package ru.webapp.everylab.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.webapp.everylab.dto.product.ProductRequest;
import ru.webapp.everylab.dto.product.ProductResponse;
import ru.webapp.everylab.dto.product.ProductTypeDto;
import ru.webapp.everylab.service.ProductService;
import ru.webapp.everylab.swagger.ProductApi;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ProductResponse addProduct(
            @RequestBody @Valid ProductRequest productRequest,
            HttpServletRequest request) {
        return productService.addProduct(productRequest, request, "userId");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<ProductResponse> getAllProductsByType(@RequestParam(name = "type") String type) {
        return productService.getAllProductsByType(type);
    }

    @GetMapping("/types")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<ProductTypeDto> getAllProductTypes() {
        return productService.getAllProductTypes();
    }
}
