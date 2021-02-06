package mg.fitapi.backend.meal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mg.fitapi.backend.day.model.Day;
import mg.fitapi.backend.day.model.Day.DayRepresentation;
import mg.fitapi.backend.product.model.Product;
import mg.fitapi.backend.product.model.Product.ProductRepresentation;
import mg.fitapi.backend.user.model.User;

import javax.persistence.*;

@Entity
@Table(name = "f_meals")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

  @Builder
  @Getter
  @AllArgsConstructor
  public static class MealRepresentation{
    private float gram;
    private DayRepresentation day;
    private ProductRepresentation product;
  }

  private static final String GENERATOR_NAME = "Meal";
  private static final String SEQUENCE_NAME = "f_meals_meal_id_seq";
  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "meal_id")
  private Long id;

  @Column(name = "gram")
  private float gram;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name ="day_id")
  private Day day;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private Product product;

  public static Meal of(float gram, Day day, Product product){
    return new Meal(null, gram, day, product);
  }

  public MealRepresentation toRepresentation() {
    return new MealRepresentation(gram, day.toRepresentation(), product.toRepresentation());
  }
}
