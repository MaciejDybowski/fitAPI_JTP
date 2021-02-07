package mg.fitapi.backend.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mg.fitapi.backend.product.controller.dto.AddProductDTO;

import javax.persistence.*;

@Entity
@Table(name = "f_products")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Builder
  @Getter
  @AllArgsConstructor
  public static class ProductRepresentation {
    private String name;
    private float protein;
    private float carbo;
    private float fat;
    private float calory;
  }


  private static final String GENERATOR_NAME = "Product";
  private static final String SEQUENCE_NAME = "f_products_product_id_seq";
  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "product_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "protein")
  private float protein;

  @Column(name = "carbo")
  private float carbo;

  @Column(name = "fat")
  private float fat;

  @Column(name = "calory")
  private float calory;

  public ProductRepresentation toRepresentation() {
    return new ProductRepresentation(name, protein, carbo, fat, calory);
  }

  public static Product of(AddProductDTO request) {
    float calory = request.getCarbo()*4 + request.getProtein()*4 + request.getFat()*9;
    return new Product(null, request.getName(), request.getProtein(), request.getCarbo(), request.getFat(), calory);}

}
