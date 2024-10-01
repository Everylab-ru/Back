package ru.webapp.everylab.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.webapp.everylab.dto.product.ProductRequest;
import ru.webapp.everylab.dto.product.ProductResponse;
import ru.webapp.everylab.dto.product.ProductTypeDto;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest, HttpServletRequest request, String userId);

    List<ProductResponse> getAllProductsByType(String type);

    List<ProductTypeDto> getAllProductTypes();
}
