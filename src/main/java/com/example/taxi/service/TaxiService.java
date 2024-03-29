package com.example.taxi.service;

import com.example.taxi.entity.Taxi;
import com.example.taxi.repository.TaxiRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TaxiService {
    TaxiRepository taxiRepository;
    @Autowired
    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public Taxi getTaxiById(Integer id){
        return taxiRepository.findById(id).orElse(null);
    }



    public List<Taxi> getAllTaxi() {
        return taxiRepository.findAll();
    }


    public Taxi createTaxi(Taxi taxi) {
        return taxiRepository.save(taxi);
    }

    public Taxi updateTaxi(Integer id, Taxi taxi) {
        taxi.setId(id);
        return taxiRepository.save(taxi);
    }

    public void deleteTaxi(Integer id) {
        taxiRepository.deleteById(id);
    }



}
