package ru.webapp.everylab.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.aspect.annotation.RequiresUserId;
import ru.webapp.everylab.dto.product.ProductRequest;
import ru.webapp.everylab.dto.product.ProductResponse;
import ru.webapp.everylab.dto.product.ProductTypeDto;
import ru.webapp.everylab.entity.product.Product;
import ru.webapp.everylab.entity.product.ProductType;
import ru.webapp.everylab.mapper.ProductMapper;
import ru.webapp.everylab.mapper.ProductTypeMapper;
import ru.webapp.everylab.repository.ProductRepository;
import ru.webapp.everylab.repository.ProductTypeRepository;
import ru.webapp.everylab.service.ProductService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductMapper productMapper;
    private final ProductTypeMapper productTypeMapper;

    @RequiresUserId
    @Transactional
    @Override
    public ProductResponse addProduct(ProductRequest productRequest, HttpServletRequest request, String userId) {
        Product product = createProduct(productRequest, userId);
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProductsByType(String type) {
        ProductType productType = productTypeRepository.findByName(type);

        return productRepository.findAllByProductType(productType)
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @Override
    public List<ProductTypeDto> getAllProductTypes() {
        return productTypeRepository.findAll()
                .stream()
                .map(productTypeMapper::toProductTypeDto)
                .toList();
    }

    private Product createProduct(ProductRequest productRequest, String userId) {
        Product product = productMapper.toProduct(productRequest);
        ProductType productType = new ProductType();
        productType.setId(productRequest.productTypeId());
        
        product.setOwnerId(UUID.fromString(userId));
        product.setProductType(productType);

        return product;
    }
}
