package mg.fitapi.backend.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.product.controller.dto.AddProductDTO;
import mg.fitapi.backend.product.model.Product.ProductRepresentation;
import mg.fitapi.backend.product.service.AddProduct;
import mg.fitapi.backend.product.service.GetProduct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProductController {

  private final GetProduct getProduct;
  private final AddProduct addProduct;

  @GetMapping("/products")
  @Operation(summary = "Pobranie wszystkich produktow")
  public List<ProductRepresentation> getAllProducts() {
    return getProduct.getAllProducts();
  }

  @PostMapping("/products")
  @Operation(summary = "Dodanie nowego produktu")
  public ProductRepresentation addNewProduct(@RequestBody AddProductDTO request) {
    return addProduct.add(request);
  }

}
