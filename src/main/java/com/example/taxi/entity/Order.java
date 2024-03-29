package com.example.taxi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;


@Entity
@Table(name = "orders")
@Component
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "driverID", referencedColumnName = "id")
    private Driver driver;
    @OneToOne
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private User user;

    private Date dateTime;
    private String startAddress;
    private String endAddress;
    private String status;

    @PrePersist
    public void prePersist() {
        this.dateTime = new Date();
    }

}
