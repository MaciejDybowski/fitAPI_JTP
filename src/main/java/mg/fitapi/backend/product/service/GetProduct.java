package mg.fitapi.backend.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.product.jpa.ProductRepository;
import mg.fitapi.backend.product.model.Product;
import mg.fitapi.backend.product.model.Product.ProductRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetProduct {

  private final ProductRepository productRepository;

  public List<ProductRepresentation> getAllProducts() {
    return productRepository.findAllProducts().stream().map(Product::toRepresentation).collect(Collectors.toList());
  }
}
