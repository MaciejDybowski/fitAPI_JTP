package mg.fitapi.backend.meal.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddMealDTO {
  private float gram;
  private LocalDate localDate;
  private String login;
  private String productName;

}
