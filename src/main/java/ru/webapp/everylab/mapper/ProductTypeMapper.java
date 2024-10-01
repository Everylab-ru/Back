package ru.webapp.everylab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.webapp.everylab.dto.product.ProductTypeDto;
import ru.webapp.everylab.entity.product.ProductType;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeMapper {
    ProductType toProductType(ProductTypeDto productType);

    @Mapping(source ="productType.id", target = "id")
    ProductTypeDto toProductTypeDto(ProductType productType);
}
