package com.example.OrderService.Service;

import com.example.OrderService.Client.ProductServiceClient;
import com.example.OrderService.Entity.Order;
import com.example.OrderService.Entity.OrderItem;
import com.example.OrderService.Repository.OrderRepository;

import com.example.OrderService.dto.CreateOrderResponseDTO;
import com.example.OrderService.dto.OrderItemDto;
import com.example.OrderService.dto.OrderRequestDTO;
import com.example.OrderService.dto.ProductDTO;
import com.example.OrderService.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;


    @Override
    // Keep order and item persistence in one transaction so partial writes are rolled back on failure.
    @Transactional
    public CreateOrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
    Order order=Order.builder().status(OrderStatus.PENDING).useId(orderRequestDTO.getUserId()).build();
    Order createOrder=orderRepository.save(order);

    List<OrderItem>newOrderItems=new ArrayList<>();
    for (OrderItemDto orderItemDto:orderRequestDTO.getItems()){
        ProductDTO productDTO=productServiceClient.getProductById(orderItemDto.getProductId());
        double pricePerUnit=productDTO.getPrice();
        double price = pricePerUnit * orderItemDto.getQuantity();

        OrderItem orderItem=OrderItem.builder().productId(orderItemDto.getProductId())
                .quantity(orderItemDto.getQuantity())
                .pricePerUnit(pricePerUnit)
                .totalPrice(price)
                .order(createOrder)
                .build();

        newOrderItems.add(orderItem);
    }
    createOrder.setItems(newOrderItems);
    orderRepository.save(createOrder);
    return CreateOrderResponseDTO.builder().orderId(createOrder.getId()).status(createOrder.getStatus()).build();
    }
}
