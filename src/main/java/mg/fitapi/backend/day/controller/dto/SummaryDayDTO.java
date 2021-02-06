package mg.fitapi.backend.day.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SummaryDayDTO {

  private String login;
  private String localDate;
}
