package com.example.OrderService.mapper;


import com.example.OrderService.Entity.Order;
import com.example.OrderService.Entity.OrderItem;
import com.example.OrderService.dto.OrderItemDto;

public class OrderItemMapper {

    public static OrderItem OrderItemRequestDTOtoOrderItemEntity(OrderItemDto itemDTO, Order order, double pricePerUnit, double totalPrice){

        return OrderItem.builder()
                .productId(itemDTO.getProductId())
                .quantity(itemDTO.getQuantity())
                .pricePerUnit(pricePerUnit)
                .totalPrice(totalPrice)
                .order(order)
                .build();
    }
}
