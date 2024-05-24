package com.example.taxi.controller;

import com.example.taxi.entity.Order;
import com.example.taxi.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Операции с заказом")
@Api
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(description = "Список всех заказов")
    @GetMapping
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(description = "Поиск заказа")
    @PostMapping("/search")
    @ResponseBody

    public List<Order> search(@RequestBody Map<String, String> searchInput) {
        String driverName = searchInput.get("searchInput");
        return orderService.searchDriver(driverName);
    }

    @Operation(description = "Новый заказ")
    @PostMapping("/new")
    public void createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
    }
}
