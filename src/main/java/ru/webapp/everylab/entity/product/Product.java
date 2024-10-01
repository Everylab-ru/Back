package ru.webapp.everylab.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webapp.everylab.entity.user.User;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String header;

    @Column(length = 5000, nullable = false)
    private String description;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false, insertable = false, updatable = false)
    private User owner;
}
