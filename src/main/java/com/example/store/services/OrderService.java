package com.example.store.services;

import com.example.store.dtos.OrderDto;
import com.example.store.entities.Order;
import com.example.store.exceptions.OrderNotFoundException;
import com.example.store.mappers.OrderMapper;
import com.example.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders(){
        var user = authService.getCurrentUser();
        var orders = orderRepository.getAllByCustomer(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithItems(orderId).orElseThrow(OrderNotFoundException::new);
        var user = authService.getCurrentUser();
        if(!order.isPlacedBy(user)){
            throw new AccessDeniedException("You don't have access to this order.");
        }
        return orderMapper.toDto(order);
    }
}
