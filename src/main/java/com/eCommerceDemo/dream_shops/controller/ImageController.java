package com.eCommerceDemo.dream_shops.controller;


import com.eCommerceDemo.dream_shops.dto.ImageDto;
import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.model.Image;
import com.eCommerceDemo.dream_shops.response.ApiResponse;
import com.eCommerceDemo.dream_shops.service.image.IIMageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/image")
public class ImageController {
    private final IIMageService iIMageService;



    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long ProductId){
        try {
            List<ImageDto> imageDtos = iIMageService.saveImages(files, ProductId);
            return ResponseEntity.ok(new ApiResponse("Images saved successfully", imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: " , e.getMessage()));
        }
    }


    @GetMapping("image/download/{ImageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long ImageId){

        Image image = iIMageService.getImageById(ImageId);
        try {
            ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                    .body(resource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @PutMapping("/image/{ImageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long ImageId, @RequestBody MultipartFile file){
        try {
            Image image =iIMageService.getImageById(ImageId);
            if (image != null){
                iIMageService.updateImage(file, ImageId);
                return ResponseEntity.ok(new ApiResponse("updated successfully", null));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error: ", e.getMessage()));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("update failed", INTERNAL_SERVER_ERROR));
    }


    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
        Image image = iIMageService.getImageById(imageId);
        try {
            if (image!=null){
                iIMageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ApiResponse("Image deleted successfully", null));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error: ", e.getMessage()));
        }
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete failed", INTERNAL_SERVER_ERROR));
    }





























}
