package mg.fitapi.backend.day.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddDayDTO {

  private String login;
  private LocalDate localDate;

}
