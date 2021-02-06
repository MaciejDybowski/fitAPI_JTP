package mg.fitapi.backend.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.fitapi.backend.product.controller.dto.AddProductDTO;
import mg.fitapi.backend.product.jpa.ProductSpringDataRepository;
import mg.fitapi.backend.product.model.Product;
import mg.fitapi.backend.product.model.Product.ProductRepresentation;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddProduct {

  private final ProductSpringDataRepository repository;

  public ProductRepresentation add(AddProductDTO request) {

    return repository.save(Product.of(request)).toRepresentation();
  }
}
