package mg.fitapi.backend.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mg.fitapi.backend.user.controller.dto.AddUserDTO;

import javax.persistence.*;

@Entity
@Table(name = "f_users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Builder
  @Getter
  @AllArgsConstructor
  public static class UserRepresentation {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private float weight;
    private float height;
    private String login;
  }


  private static final String GENERATOR_NAME = "User";
  private static final String SEQUENCE_NAME = "f_users_user_id_seq";
  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "age")
  private int age;

  @Column(name = "gender")
  private String gender;

  @Column(name = "weight")
  private float weight;

  @Column(name = "height")
  private float height;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  public UserRepresentation toRepresentation() {
    return new UserRepresentation(firstName, lastName, age, gender, weight, height, login);
  }

  public static User of(AddUserDTO request) {
    return new User(null, request.getFirstName(), request.getLastName(), request.getAge(), request.getGender(), request.getWeight(), request.getHeight(), request.getLogin(), request.getPassword());
  }

}
