package ru.webapp.everylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webapp.everylab.entity.product.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

    ProductType findByName(String productTypeName);
}
