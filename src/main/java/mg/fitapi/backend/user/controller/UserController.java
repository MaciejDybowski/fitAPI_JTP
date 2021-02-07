package mg.fitapi.backend.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.user.controller.dto.AddUserDTO;
import mg.fitapi.backend.user.controller.dto.LoginCredentials;
import mg.fitapi.backend.user.model.User;
import mg.fitapi.backend.user.model.User.UserRepresentation;
import mg.fitapi.backend.user.service.AddUser;
import mg.fitapi.backend.user.service.GetUser;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class UserController {
  private final GetUser getUser;
  private final AddUser addUser;

  @GetMapping("/users")
  public List<UserRepresentation> getAllUsers() {
    return getUser.getAllUsers();
  }

  @PostMapping("/register")
  @Operation(summary = "Rejestracja usera")
  public UserRepresentation addUser(@RequestBody AddUserDTO request) {
    return addUser.addUser(request);
  }

  @GetMapping("/login")
  @Operation(summary = "Logowanie")
  public UserRepresentation loginToApp(@Parameter LoginCredentials request) {
    return getUser.login(request);
  }

  @GetMapping("/users/info")
  @Operation(summary = "Pobranie informacji o userze")
  public UserRepresentation getDetails(@Parameter String login){
    return getUser.getOneUser(login);
  }


}
