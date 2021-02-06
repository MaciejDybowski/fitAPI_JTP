package mg.fitapi.backend.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.user.controller.dto.AddUserDTO;
import mg.fitapi.backend.user.jpa.UserSpringDataRepository;
import mg.fitapi.backend.user.model.User;
import mg.fitapi.backend.user.model.User.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddUser {

  private final UserSpringDataRepository userSpringDataRepository;

  public UserRepresentation addUser(AddUserDTO request) {
    User user = userSpringDataRepository.save(User.of(request));
    return user.toRepresentation();
  }

}
