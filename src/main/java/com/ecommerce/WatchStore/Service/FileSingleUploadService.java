package com.ecommerce.WatchStore.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSingleUploadService {

    private final Path uploadDir = Paths.get("uploads");

    public FileSingleUploadService() throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
    }

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        Path destination = uploadDir.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    public boolean validateFile(MultipartFile file, long maxSize, String... allowedContentTypes) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("File is too large! Maximum size is " + maxSize / (1024 * 1024) + "MB");
        }
        String contentType = file.getContentType();
        boolean isValidContentType = false;
        for (String allowedContentType : allowedContentTypes) {
            if (contentType != null && contentType.startsWith(allowedContentType)) {
                isValidContentType = true;
                break;
            }
        }
        if (!isValidContentType) {
            throw new IllegalArgumentException("File must be one of the allowed types: " + String.join(", ", allowedContentTypes));
        }
        return true;
    }
}

