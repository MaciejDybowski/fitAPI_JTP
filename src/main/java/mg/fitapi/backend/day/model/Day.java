package mg.fitapi.backend.day.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mg.fitapi.backend.user.model.User;
import mg.fitapi.backend.user.model.User.UserRepresentation;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "f_days")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Day {

  @Builder
  @Getter
  @AllArgsConstructor
  public static class DayRepresentation {
    private LocalDate date;
    private int water;
    private UserRepresentation user;
    private float totalCalory;
  }

  @Builder
  @Getter
  @AllArgsConstructor
  public static class DaySummary {
    private int water;
    private float protein;
    private float carbs;
    private float fat;
    private float totalCalory;
  }



  private static final String GENERATOR_NAME = "Day";
  private static final String SEQUENCE_NAME = "f_days_day_id_seq";
  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "day_id")
  private Long id;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "water")
  private int water;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name ="user_id")
  private User user;

  public DayRepresentation toRepresentation() {
    return new DayRepresentation(date, water, user.toRepresentation(), 0);
  }
  public DayRepresentation toRepresentation(float totalCalory) {
    return new DayRepresentation(date, water, user.toRepresentation(), totalCalory);
  }

  public static Day of(User user, LocalDate localDate){
    return new Day(null, localDate, 0, user);
  }
}
