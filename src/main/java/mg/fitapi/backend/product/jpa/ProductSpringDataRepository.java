package mg.fitapi.backend.product.jpa;

import mg.fitapi.backend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpringDataRepository extends JpaRepository<Product, Long> {
}
