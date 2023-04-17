package kz.bek.springfileandservice.services.impl;

import kz.bek.springfileandservice.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public boolean uploadPhoto(MultipartFile multipartFile, Long id) {
        try {
            byte bytes[] = multipartFile.getBytes();
            String fileName = "photo" + id;
            String filePath = "build/resources/main/static/" + fileName + ".jpg";
            Path path = Paths.get(filePath);
            Files.write(path,bytes);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
