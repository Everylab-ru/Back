package ru.webapp.everylab.entity.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "nsi_product_type")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "productType")
    private Set<Product> products;
}
