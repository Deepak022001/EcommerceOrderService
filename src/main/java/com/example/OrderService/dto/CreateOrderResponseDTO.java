package com.example.OrderService.dto;

import com.example.OrderService.enums.OrderStatus;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponseDTO {
    private Long orderId;
    private OrderStatus status;
}
