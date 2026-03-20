package com.example.OrderService.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderItemDto {
    private Long productId;
    private int quantity;
}
