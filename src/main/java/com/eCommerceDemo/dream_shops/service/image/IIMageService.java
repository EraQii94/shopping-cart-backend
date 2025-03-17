package com.eCommerceDemo.dream_shops.service.image;

import com.eCommerceDemo.dream_shops.dto.ImageDto;
import com.eCommerceDemo.dream_shops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IIMageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long ProductId);
    void updateImage(MultipartFile file, Long ImageId);

}
