package mg.fitapi.backend.day.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.day.controller.dto.SummaryDayDTO;
import mg.fitapi.backend.day.model.Day;
import mg.fitapi.backend.day.model.Day.DaySummary;
import mg.fitapi.backend.day.repository.DayQDSLRepository;
import mg.fitapi.backend.meal.model.Meal;
import mg.fitapi.backend.meal.repository.MealRepositroy;
import mg.fitapi.backend.user.excpetions.UserNotFoundException;
import mg.fitapi.backend.user.jpa.UserQDSLRepository;
import mg.fitapi.backend.user.model.User;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetDay {
  private final MealRepositroy mealRepositroy;
  private final UserQDSLRepository userQDSLRepository;
  private final DayQDSLRepository dayQDSLRepository;

  public DaySummary getSummaryDay(SummaryDayDTO request){

    User user = userQDSLRepository.findByLogin(request.getLogin())
            .orElseThrow(() -> {throw new UserNotFoundException(request.getLogin());
            });

    LocalDate localDate = LocalDate.parse(request.getLocalDate());

    Day day = dayQDSLRepository.findByUserAndDate(user, localDate)
            .orElseThrow(() -> {throw new RuntimeException();});

    List<Meal> meals = mealRepositroy.findAllByUserAndDate(user, localDate);

    float protein = 0;
    float carbs = 0;
    float fat = 0;
    float totalCalory = 0;

    for(int i = 0 ; i < meals.size() ; i++){
      protein += meals.get(i).getGram()/100 * meals.get(i).getProduct().getProtein();
      carbs += meals.get(i).getGram()/100 * meals.get(i).getProduct().getCarbo();
      fat += meals.get(i).getGram()/100 * meals.get(i).getProduct().getFat();
    }

    totalCalory = protein*4 + carbs*4 + fat*9;

    return new DaySummary(day.getWater(), protein, carbs, fat,totalCalory);
  }

}
