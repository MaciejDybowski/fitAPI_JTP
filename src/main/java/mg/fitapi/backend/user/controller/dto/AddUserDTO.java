package mg.fitapi.backend.user.controller.dto;

import lombok.Data;

@Data
public class AddUserDTO {

  private String firstName;
  private String lastName;
  private int age;
  private float weight;
  private float height;
  private String login;
  private String password;

}
