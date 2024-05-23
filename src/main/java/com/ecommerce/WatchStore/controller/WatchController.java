package com.ecommerce.WatchStore.controller;
import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.dto.WatchDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/watch")
public class WatchController {

    @GetMapping("")
    public ResponseData<?> getAllWatch(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return new ResponseData<>(HttpStatus.OK.value(), "success", String.format("getAllWatchProduct, page = %d, limit = %d", page, limit));
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneWatch(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "WatchId: ", 1);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData<?> createWatch(@Valid @ModelAttribute WatchDTO watchDTO) {
        MultipartFile file = watchDTO.getFile();
        if (file == null ||  file.isEmpty()) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "File is required");
        }

        try {
            if (file.getSize() > 10 * 1024 * 1024) {
                return new ResponseError(HttpStatus.BAD_REQUEST.value(), "File is too large! Maximum size is 10MB");
            }

            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "File must be an image");
            }

            String fileName = storeFile(file);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Watch added successfully!", fileName);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }


    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneWatch(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Watch deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneWatch(@Valid @RequestBody WatchDTO watchDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Watch updated successfully!");
    }
}
