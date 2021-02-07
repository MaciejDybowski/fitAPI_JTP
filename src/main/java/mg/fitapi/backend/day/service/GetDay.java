package mg.fitapi.backend.day.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.day.controller.dto.SummaryDayDTO;
import mg.fitapi.backend.day.model.Day;
import mg.fitapi.backend.day.model.Day.DayRepresentation;
import mg.fitapi.backend.day.model.Day.DaySummary;
import mg.fitapi.backend.day.repository.DayQDSLRepository;
import mg.fitapi.backend.day.repository.DaySpringDataRepository;
import mg.fitapi.backend.meal.model.Meal;
import mg.fitapi.backend.meal.model.Meal.MealRepresentation;
import mg.fitapi.backend.meal.repository.MealRepositroy;
import mg.fitapi.backend.user.excpetions.UserNotFoundException;
import mg.fitapi.backend.user.jpa.UserQDSLRepository;
import mg.fitapi.backend.user.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetDay {
  private final MealRepositroy mealRepositroy;
  private final UserQDSLRepository userQDSLRepository;
  private final DayQDSLRepository dayQDSLRepository;
  private final DaySpringDataRepository daySpringDataRepository;

  public DaySummary getSummaryDay(SummaryDayDTO request) {

    User user = userQDSLRepository.findByLogin(request.getLogin())
            .orElseThrow(() -> {
              throw new UserNotFoundException(request.getLogin());
            });

    LocalDate localDate = LocalDate.parse(request.getLocalDate());

    Day day;
    if (dayQDSLRepository.findByUserAndDate(user, localDate).isEmpty()) {
      day = daySpringDataRepository.save(Day.of(user, localDate));
    } else {
      day = dayQDSLRepository.findByUserAndDate(user, localDate)
              .orElseThrow();
    }


    List<Meal> meals = mealRepositroy.findAllByUserAndDate(user, localDate);

    int protein = 0;
    int carbs = 0;
    int fat = 0;
    int totalCalory = 0;

    for (int i = 0; i < meals.size(); i++) {
      protein += meals.get(i).getGram() / 100 * meals.get(i).getProduct().getProtein();
      carbs += meals.get(i).getGram() / 100 * meals.get(i).getProduct().getCarbo();
      fat += meals.get(i).getGram() / 100 * meals.get(i).getProduct().getFat();
    }

    totalCalory = protein * 4 + carbs * 4 + fat * 9;

    return new DaySummary(day.getWater(), protein, carbs, fat, totalCalory);
  }

  public List<MealRepresentation> getAllMealsOfDay(String localDateString, String login) {
    LocalDate localDate = LocalDate.parse(localDateString);

    User user = userQDSLRepository.findByLogin(login)
            .orElseThrow(() -> {
              throw new UserNotFoundException(login);
            });

    return mealRepositroy.findAllByUserAndDate(user, localDate).stream().map(m -> m.toRepresentation(m.getProduct().toRepresentation())).collect(Collectors.toList());
  }

  public DayRepresentation addWater(String localDateString, String login) {

    User user = userQDSLRepository.findByLogin(login)
            .orElseThrow(() -> {
              throw new UserNotFoundException(login);
            });

    LocalDate localDate = LocalDate.parse(localDateString);

    Day day = dayQDSLRepository.findByUserAndDate(user, localDate)
            .orElseThrow();

    return daySpringDataRepository.save(day.addWater()).toRepresentation();
  }
}
