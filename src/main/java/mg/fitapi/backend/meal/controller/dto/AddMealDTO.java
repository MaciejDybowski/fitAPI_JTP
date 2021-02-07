package mg.fitapi.backend.meal.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddMealDTO {
  private float gram;
  private String date;
  private String login;
  private String productName;

}
