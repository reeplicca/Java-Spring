package kz.kenzhakhimov.goodplaces.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean uploadPhoto(MultipartFile file);
}
