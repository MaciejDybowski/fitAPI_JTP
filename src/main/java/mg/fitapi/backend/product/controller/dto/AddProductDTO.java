package mg.fitapi.backend.product.controller.dto;

import lombok.Data;

@Data
public class AddProductDTO {

  private String name;
  private int protein;
  private int carbo;
  private int fat;
}
