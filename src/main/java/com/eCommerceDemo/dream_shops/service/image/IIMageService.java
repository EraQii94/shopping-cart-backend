package com.eCommerceDemo.dream_shops.service.image;

import com.eCommerceDemo.dream_shops.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IIMageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    Image saveImage(MultipartFile file, Long ProductId);
    void updateImage(MultipartFile file, Long ImageId);

}
