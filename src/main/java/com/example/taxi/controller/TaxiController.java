package com.example.taxi.controller;

import com.example.taxi.entity.Taxi;
import com.example.taxi.service.TaxiService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Операции с авто")
@Api
@RestController
@RequestMapping("/taxi")
@CrossOrigin(origins = "*")
public class TaxiController {

    TaxiService taxiService;

    @Autowired
    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Operation(description = "Добавление авто")
    @PostMapping("/new")
    public Taxi create(@RequestBody Taxi taxi) {
        return taxiService.createTaxi(taxi);
    }

    @Operation(description = "Авто с водилой")
    @GetMapping("/show")
    @ResponseBody
    public List<Taxi> getAllDriver() {
        return taxiService.getAllTaxi();
    }

    @Operation(description = "Удалить авто")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        taxiService.deleteTaxi(id);
    }

    @Operation(description = "Обновление авто")
    @PutMapping("/update/{id}")
    public Taxi edit(@PathVariable("id") int id, @RequestBody Taxi taxi) {
        return taxiService.updateTaxi(id, taxi);
    }

    @Operation(description = "Информация об атво")
    @GetMapping("/{id}")
    public Taxi showIdTaxi(@PathVariable("id") int id) {
        return taxiService.getTaxiById(id);
    }

}
