package com.eCommerceDemo.dream_shops.service.image;

import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.model.Image;
import com.eCommerceDemo.dream_shops.repository.ImageRepository;
import com.eCommerceDemo.dream_shops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;

import java.io.IOException;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class ImageService implements IIMageService{

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
    }

    @Override
    public void deleteImageById(Long id) {

        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("Image not found");
        });
    }

    @Override
    public Image saveImage(MultipartFile file, Long ProductId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long ImageId) {

        Image image = getImageById(ImageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
