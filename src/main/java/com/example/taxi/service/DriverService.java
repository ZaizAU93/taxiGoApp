package com.example.taxi.service;

import com.example.taxi.entity.Driver;
import com.example.taxi.repository.DriverRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@Service
public class DriverService {

    @Value("${upload.dir}") // Путь к директории для сохранения файлов
    private String uploadDir;

    private DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Integer id) {
        return driverRepository.findById(id).orElse(null);
    }

//    public Driver getByLogin(String login) {
//        List<Driver> drivers = driverRepository.findAll();
//        return drivers.stream().filter(driver -> driver.equals(login)).
//                findFirst().orElse(null);
//    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Integer id, Driver driver) {
        driver.setId(id);
        return driverRepository.save(driver);
    }

    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
    }

    public Driver updateStatus(Driver driver) {
        driver.setStatus(true);
        return driverRepository.save(driver);
    }

    // загрузка фотки на сервер
    public String uploadFile(MultipartFile multipartFile, Driver driver) {
        if (multipartFile.isEmpty()) {
            return "Ошибка при загрузке файла";
        }
        try {
            File uploadDir = new File("uploads");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Создание директории, если ее нет
            }

            String filePath = uploadDir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename();
            String filPatchDB = multipartFile.getOriginalFilename();
            System.out.println(filePath);
            File dest = new File(filePath);
            multipartFile.transferTo(dest);

            driver.setImgUrl(filPatchDB);
            driverRepository.save(driver);
            return "Файл успешно загружен в " + uploadDir;
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при сохранении файла";
        }
    }


}
