package mg.fitapi.backend.product.jpa;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import mg.fitapi.backend.product.model.Product;
import mg.fitapi.backend.product.model.QProduct;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Product> findAllProducts() {
    JPAQuery<Product> q = new JPAQueryFactory(entityManager)
            .selectFrom(QProduct.product).fetchAll();

    return q.fetch();
  }

  public Optional<Product> findAllByName(String productName) {
    JPAQuery<Product> q = new JPAQueryFactory(entityManager)
            .selectFrom(QProduct.product)
            .where(QProduct.product.name.eq(productName));
    return Optional.ofNullable(q.fetchOne());
  }
}
