package com.example.taxi.controller;

import com.example.taxi.entity.User;
import com.example.taxi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Операции с пользователем")
@Api
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")

public class UsersController {
    UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "Создать пользователя")
    @PostMapping("/new")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(description = "Полный список пользователей")
    @GetMapping("/show")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @Operation(description = "Удаление пользователя")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    @Operation(description = "Обновление пользователя")
    @PutMapping("/update/{id}")
    public User edit(@PathVariable("id") int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Operation(description = "Информация о пользователе")
    @GetMapping("/{id}")
    public User showIdUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @Operation(description = "Загрузка фотки пользователя")
    @PostMapping("/{id}/upload")
    public String uploadImg(@RequestParam("file") MultipartFile multipartFile, @PathVariable int id) {
        User user = userService.getUserById(id);
        userService.uploadFile(multipartFile, user);
        return "файл успешно загружен";
    }
}
