package com.example.OrderService.Repository;

import com.example.OrderService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


public interface OrderRepository extends JpaRepository<Order,Long> {
}
