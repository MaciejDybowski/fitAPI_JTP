package mg.fitapi.backend.user.controller.dto;

import lombok.Data;

@Data
public class LoginCredentials {
  private String login;
  private String password;
}
