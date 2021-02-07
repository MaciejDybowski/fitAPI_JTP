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
    private float weight;
    private float height;
    private String login;
    private float demandProtein;
    private float demandFat;
    private float demandCarbs;
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

  @Column(name = "weight")
  private float weight;

  @Column(name = "height")
  private float height;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "daily_demand_kcl")
  private int dailyKcal;

  @Column(name = "daily_demand_protein")
  private int dailyProtein;

  @Column(name = "daily_demand_carbs")
  private int dailyCarbs;

  @Column(name = "daily_demand_fat")
  private int dailyFat;

  public UserRepresentation toRepresentation() {
    return new UserRepresentation(firstName, lastName, age, weight, height, login, dailyProtein, dailyFat, dailyCarbs);
  }

  public static User of(AddUserDTO r) {

    int dailyDemand = (int) (66 + (13.7 * r.getAge()) + (5 * r.getHeight()) - (6.8 * r.getAge()));
    int protein = (int) (r.getWeight() * 2);
    int fat = (int) ((dailyDemand * 0.25) / 9);
    int carbs = (int) ((dailyDemand - protein * 4) - (dailyDemand * 0.25)) / 4;

    return new User(null, r.getFirstName(), r.getLastName(), r.getAge(), r.getWeight(), r.getHeight(), r.getLogin(), r.getPassword(), dailyDemand, protein, carbs, fat);
  }

}
