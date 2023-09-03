package com.michaelvol.ecommerceapi.product.utils;

import com.michaelvol.ecommerceapi.product.Product;
import com.michaelvol.ecommerceapi.product.dto.UpdateProductRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UpdateProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(UpdateProductRequest dto, @MappingTarget Product product);
}
