package mg.fitapi.backend.meal.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.meal.controller.dto.AddMealDTO;
import mg.fitapi.backend.meal.model.Meal.MealRepresentation;
import mg.fitapi.backend.meal.service.AddMeal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MealController {
  private final AddMeal addMeal;

  @PostMapping("/meals")
  @Operation(summary = "Dodanie posiłku dla usera")
  public MealRepresentation addMeal(@RequestBody AddMealDTO request){
    return addMeal.add(request);
  }
}
