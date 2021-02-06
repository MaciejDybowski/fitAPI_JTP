package mg.fitapi.backend.day.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.day.controller.dto.AddDayDTO;
import mg.fitapi.backend.day.controller.dto.SummaryDayDTO;
import mg.fitapi.backend.day.model.Day.DayRepresentation;
import mg.fitapi.backend.day.model.Day.DaySummary;
import mg.fitapi.backend.day.service.AddDay;
import mg.fitapi.backend.day.service.GetDay;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DayController {

  private final AddDay addDay;
  private final GetDay getDay;

  @PostMapping("/days")
  @Operation(summary = "Dodanie dnia do bazy danych")
  public DayRepresentation add(@RequestBody AddDayDTO request) {
    return addDay.add(request);
  }


  @GetMapping("/days/details")
  @Operation(summary = "Pobranie podsumowania dnia dla usera")
  public DaySummary getSummary(@Parameter SummaryDayDTO request){
    return getDay.getSummaryDay(request);
  }

}
