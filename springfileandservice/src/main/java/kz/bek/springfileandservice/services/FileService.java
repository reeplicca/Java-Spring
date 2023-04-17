package kz.bek.springfileandservice.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean uploadPhoto(MultipartFile multipartFile, Long id);
}
