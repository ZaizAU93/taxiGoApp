package com.example.taxi.service;

import com.example.taxi.entity.User;
import com.example.taxi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@Data
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User findUserByUsername(String username) {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) ;
            User user = users.get(i);
            return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        return user;
//               org.springframework.security.core.userdetails.User.builder()
//               .username(user.getUsername())
//               .password(user.getPassword())
//               .roles(String.valueOf(user.getRole())).build();
    }

    public String uploadFile(MultipartFile multipartFile, User user) {
        if (multipartFile.isEmpty()) {
            return "Ошибка при загрузке файла";
        }
        try {
            File uploadDir = new File("static/uploads");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Создание директории, если ее нет
            }

            String filePath = uploadDir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename();
            String filPatchDB = multipartFile.getOriginalFilename();
            System.out.println(filePath);
            File dest = new File(filePath);
            multipartFile.transferTo(dest);

            user.setImgUrl("/img_project/" + filPatchDB);
            userRepository.save(user);
            return "Файл успешно загружен в " + uploadDir;
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при сохранении файла";
        }
    }


}
