package mg.fitapi.backend.day.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.day.controller.dto.AddDayDTO;
import mg.fitapi.backend.day.controller.dto.SummaryDayDTO;
import mg.fitapi.backend.day.model.Day.DayRepresentation;
import mg.fitapi.backend.day.model.Day.DaySummary;
import mg.fitapi.backend.day.service.GetDay;
import mg.fitapi.backend.meal.model.Meal.MealRepresentation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DayController {

  private final GetDay getDay;

  @GetMapping("/days/details")
  @Operation(summary = "Pobranie podsumowania dnia dla usera")
  public DaySummary getSummary(@Parameter SummaryDayDTO request) {
    return getDay.getSummaryDay(request);
  }

  @GetMapping("/days/{localDate}/meals")
  @Operation(summary = "Pobranie wszystkich posiłkow z danego dnia")
  public List<MealRepresentation> getDailyMeals(@PathVariable String localDate, @Parameter String login) {
    return getDay.getAllMealsOfDay(localDate, login);
  }

  @PostMapping("/days/{localDate}/water")
  @Operation(summary = "Dodanie szklanki wody")
  public DayRepresentation addWater(@PathVariable String localDate, @RequestBody String login) {
    return getDay.addWater(localDate, login);
  }

}
