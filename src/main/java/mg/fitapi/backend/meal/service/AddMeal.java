package mg.fitapi.backend.meal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.day.model.Day;
import mg.fitapi.backend.day.repository.DayQDSLRepository;
import mg.fitapi.backend.day.repository.DaySpringDataRepository;
import mg.fitapi.backend.meal.controller.dto.AddMealDTO;
import mg.fitapi.backend.meal.model.Meal;
import mg.fitapi.backend.meal.model.Meal.MealRepresentation;
import mg.fitapi.backend.meal.repository.MealSpringDataRepository;
import mg.fitapi.backend.product.jpa.ProductRepository;
import mg.fitapi.backend.product.model.Product;
import mg.fitapi.backend.user.excpetions.UserNotFoundException;
import mg.fitapi.backend.user.jpa.UserQDSLRepository;
import mg.fitapi.backend.user.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddMeal {
  private final DayQDSLRepository dayRepository;
  private final DaySpringDataRepository daySpringDataRepository;
  private final MealSpringDataRepository repository;
  private final UserQDSLRepository userQDSLRepository;
  private final ProductRepository productRepository;

  public MealRepresentation add(AddMealDTO request) {

    // znalezienie usera w bazie
    User user = userQDSLRepository.findByLogin(request.getLogin())
            .orElseThrow(() -> {throw new UserNotFoundException(request.getLogin());
            });

    // znalezienie odpowiedniego dnia, jezeli nie ma to znaczy ze pierwszy dzien i robi inserta
    Day day;
    LocalDate localDate = LocalDate.parse(request.getDate());
    if( dayRepository.findByUserAndDate(user, localDate).isEmpty() ){
      day = daySpringDataRepository.save(new Day(null, localDate ,0, user));
    } else {
      day = dayRepository.findByUserAndDate(user, localDate)
              .orElseThrow(() -> {throw new RuntimeException();});
    }



    // szukanie produktu po nazwie
    Product product = productRepository.findAllByName(request.getProductName())
            .orElseThrow(() -> {throw new RuntimeException();});


    return repository.save(Meal.of(request.getGram(), day, product)).toRepresentation(product.toRepresentation());
  }

}
