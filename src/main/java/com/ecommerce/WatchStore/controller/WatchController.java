package com.ecommerce.WatchStore.controller;
import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Service.FileSingleUploadService;
import com.ecommerce.WatchStore.dto.WatchDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("${api.prefix}/watches")
@RequiredArgsConstructor
public class WatchController {

    private final FileSingleUploadService fileSingleUploadService;

    @GetMapping("")
    public ResponseData<?> getAllWatch(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return new ResponseData<>(HttpStatus.OK.value(), "success", String.format("getAllWatchProduct, page = %d, limit = %d", page, limit));
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneWatch(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "WatchId: ", 1);
    }

    @PostMapping(value = "",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseData<?> createWatch(@Valid @ModelAttribute WatchDTO watchDTO) {
        try {
            List<MultipartFile> files = watchDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            if (files.size() < 2) return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Files is required");
            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue;
                }
                fileSingleUploadService.validateFile(file, 10 * 1024 * 1024, "image/");
                String fileName = fileSingleUploadService.storeFile(file);
            }
            return new ResponseData<>(HttpStatus.CREATED.value(), "Successfully!", watchDTO.getProductName());
        } catch (IllegalArgumentException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        } catch (IOException e) {
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
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
