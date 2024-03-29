package com.example.taxi.service;

import com.example.taxi.entity.Driver;
import com.example.taxi.entity.Order;
import com.example.taxi.repository.DriverRepository;
import com.example.taxi.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    DriverRepository driverRepository;
    OrderRepository orderRepository;
    EntityManager entityManager;
    @Autowired
    public OrderService(OrderRepository orderRepository, EntityManager entityManager, DriverRepository driverRepository) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
        this.driverRepository = driverRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> searchDriver(String name){
        String driverName = name;

        String jpql = "SELECT o FROM Order o JOIN Driver d ON o.driver.id = d.id WHERE d.name = :driverName";

        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("driverName", driverName);

        List<Order> orders = query.getResultList();

        return orders;
    }

    public Order createOrder(Order order){
        String jpql = "SELECT driver FROM Driver driver WHERE driver.status = true ";
        TypedQuery<Driver> query = entityManager.createQuery(jpql, Driver.class);
        List<Driver> resultList = query.getResultList();
        if (resultList.isEmpty()){
            String message = "в настоящий момент нет свободных водителей";
        } else {
            Driver driver = driverRepository.findById(resultList.get(0).getId()).orElseThrow(() -> new EntityNotFoundException("Driver not found"));
            order.setDriver(driver);
            orderRepository.save(order);
        }

        return orderRepository.save(order);
    }




}
