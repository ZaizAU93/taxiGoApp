package com.example.taxi.controller;

import com.example.taxi.entity.Driver;
import com.example.taxi.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Tag(name = "Операции с водителем")
@Api
@RestController
@RequestMapping("/drivers")
@CrossOrigin(origins = "*")
public class DriverController {


    DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @Operation(description = "Создание водителя")
    @PostMapping("/new")
    public Driver create(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    @Operation(description = "Показать всех водителей")
    @GetMapping("/show")
    @ResponseBody
    public List<Driver> getAllDriver() {
        return driverService.getAllDriver();
    }

    @Operation(description = "Удалить водителя")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        driverService.deleteDriver(id);
    }

    @Operation(description = "Обновить водителя")
    @PutMapping("/update/{id}")
    public Driver edit(@PathVariable("id") int id, @RequestBody Driver driver) {
        return driverService.updateDriver(id, driver);
    }

    @Operation(description = "Информация о водителе")
    @GetMapping("/{id}")
    public Driver showIdDiver(@PathVariable("id") int id) {
        return driverService.getDriverById(id);
    }

    //Обновление статуса у водителя
    @Operation(description = "Обновление статуса водителя")
    @PostMapping("/status/{id}")
    public Driver updateStatus(@PathVariable int id) {
        Driver driver = driverService.getDriverById(id);
        return driverService.updateStatus(driver);
    }

    @Operation(description = "Загрузка фотографии водителя")
    @PostMapping("/{id}/upload")
    public String uploadImg(@RequestParam("file") MultipartFile multipartFile, @PathVariable int id) {
        Driver driver = driverService.getDriverById(id);
        driverService.uploadFile(multipartFile, driver);
        return "файл успешно загружен";
    }

    @ApiIgnore
    @GetMapping("/Demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok(" токен работат");
    }
}