package com.michaelvol.ecommerceapi.order.utils;

import com.michaelvol.ecommerceapi.order.Order;
import com.michaelvol.ecommerceapi.order.dto.UpdateOrderRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UpdateOrderMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromDto(UpdateOrderRequest updateOrderRequest, @MappingTarget Order order);
}
