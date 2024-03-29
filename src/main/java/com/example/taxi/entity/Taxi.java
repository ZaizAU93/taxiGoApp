package com.example.taxi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "taxi")
@Data
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "carModel")
    private String carModel;
    @Column(name = "carBrand")
    private String carBrand;

    @Column(name = "color")
    private String color;

    @Column(name = "available")
    private boolean available;

    @Column(name = "yearOfRelease")
    private String yearOfRelease;

    @OneToOne
    @JoinColumn(name = "driverID", referencedColumnName = "id")
    private Driver driver;
}
