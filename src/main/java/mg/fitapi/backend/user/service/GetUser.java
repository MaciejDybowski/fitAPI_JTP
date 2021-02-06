package mg.fitapi.backend.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.user.controller.dto.LoginCredentials;
import mg.fitapi.backend.user.excpetions.UserNotFoundException;
import mg.fitapi.backend.user.model.User;
import mg.fitapi.backend.user.jpa.UserQDSLRepository;
import mg.fitapi.backend.user.model.User.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUser {

  private final UserQDSLRepository userQDSLRepository;

  public List<UserRepresentation> getAllUsers () {
    return userQDSLRepository.findAll()
            .stream().map(u -> u.toRepresentation())
            .collect(Collectors.toList());
  }

  public UserRepresentation login(LoginCredentials request) {
    User user = userQDSLRepository.findByLogin(request.getLogin())
            .orElseThrow(() -> {throw new UserNotFoundException(request.getLogin());});

    if(request.getPassword().equals(user.getPassword())){
      return user.toRepresentation();
    }else{
      return null;
    }
  }
}
