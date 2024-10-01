package ru.webapp.everylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webapp.everylab.entity.product.Product;
import ru.webapp.everylab.entity.product.ProductType;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByProductType(ProductType productType);
}
