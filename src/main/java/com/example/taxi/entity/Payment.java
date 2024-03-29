package com.example.taxi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    private double amount;
    private String status;
}
