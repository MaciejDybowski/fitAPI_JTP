package mg.fitapi.backend.day.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.day.controller.dto.AddDayDTO;
import mg.fitapi.backend.day.model.Day;
import mg.fitapi.backend.day.model.Day.DayRepresentation;
import mg.fitapi.backend.day.repository.DaySpringDataRepository;
import mg.fitapi.backend.user.excpetions.UserNotFoundException;
import mg.fitapi.backend.user.jpa.UserQDSLRepository;
import mg.fitapi.backend.user.model.User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddDay {
  private final DaySpringDataRepository repository;
  private final UserQDSLRepository userQDSLRepository;

  public DayRepresentation add(AddDayDTO request) {
    User user = userQDSLRepository.findByLogin(request.getLogin())
            .orElseThrow(() -> {throw new UserNotFoundException(request.getLogin());
            });

    return repository.save(Day.of(user, request.getLocalDate())).toRepresentation(0);
  }
}
