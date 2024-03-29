package com.example.taxi.controller;

import com.example.taxi.entity.User;
import com.example.taxi.service.DriverService;
import com.example.taxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    DriverService driverService;
    UserService userService;

    @Autowired
    public UploadController(DriverService driverService, UserService userService) {
        this.driverService = driverService;
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<String> upload(@RequestBody MultipartFile multipartFile, @RequestBody User user) {
        userService.uploadFile(multipartFile, user);
        return ResponseEntity.ok("Успешная загрузка");
    }
}
