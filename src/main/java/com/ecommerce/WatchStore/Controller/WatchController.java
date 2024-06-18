package com.ecommerce.WatchStore.Controller;
import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Model.Watch;
import com.ecommerce.WatchStore.Model.WatchImage;
import com.ecommerce.WatchStore.ResponseData.ProductResponse;
import com.ecommerce.WatchStore.Service.FileSingleUploadService;
import com.ecommerce.WatchStore.Service.WatchService;
import com.ecommerce.WatchStore.Service.impl.WatchServiceImpl;
import com.ecommerce.WatchStore.dto.WatchDTO;
import com.ecommerce.WatchStore.dto.WatchImageDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final WatchService watchService;


    @GetMapping("")
    public ResponseData<?> getAllWatch(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page, limit);
            Page<Watch> watchPage = watchService.getAllProducts(pageRequest);
            int totalPage = watchPage.getTotalPages();
            int currentPage = watchPage.getNumber();
            List<Watch> watches = watchPage.getContent();
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Product details: ",
                    ProductResponse.builder()
                            .watches(watches)
                            .totalPages(totalPage)
                            .currentPage(currentPage)
                            .build()
                    );
        } catch (Exception e) {
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneWatch(@PathVariable Long id) {
        try {
            Watch productDetails = watchService.getProductById(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Product details: ", productDetails);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @PostMapping(value = "",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseData<?> createWatch(@Valid @ModelAttribute WatchDTO watchDTO) {
        try {
            Watch newWatch = watchService.createProduct(watchDTO);
            List<MultipartFile> files = watchDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            if (files.size() < 2) return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Files is required");

            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue;
                }
                fileSingleUploadService.validateFile(file, 10 * 1024 * 1024, "image/");
                String fileName = fileSingleUploadService.storeFile(file);
                WatchImage watchImage = watchService.createWatchImage(
                        newWatch.getId(),
                        WatchImageDTO.builder()
                                .imageUrl(fileName)
                                .build());
            }
            return new ResponseData<>(HttpStatus.CREATED.value(), "The product has been added successfully!", newWatch.getId());
        } catch (IllegalArgumentException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneWatch(@PathVariable @Min(1) Long id) {
        try {
            watchService.deleteProduct(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Watch deleted successfully!");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneWatch(@Valid @RequestBody WatchDTO watchDTO, @PathVariable Long id) {
        try {
            Watch updateProduct = watchService.updateProduct(id, watchDTO);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Watch updated successfully!", updateProduct);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
