package com.eCommerceDemo.dream_shops.service.product;

import com.eCommerceDemo.dream_shops.exceptions.ProductNotFoundException;
import com.eCommerceDemo.dream_shops.model.Category;
import com.eCommerceDemo.dream_shops.model.Product;
import com.eCommerceDemo.dream_shops.repository.CategoryRepository;
import com.eCommerceDemo.dream_shops.repository.ImageRepository;
import com.eCommerceDemo.dream_shops.repository.ProductRepository;
import com.eCommerceDemo.dream_shops.request.AddProductRequest;
import com.eCommerceDemo.dream_shops.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;


    @Override
    public Product addProduct(AddProductRequest request) {

        //check if the product already exists
        //if yes set it as the new product category
        //if no, then save it as a new category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() ->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }
//done
    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product by id " + productId + " was not found"));
    }
//done
    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId).ifPresentOrElse(productRepository::delete,
                ()->{throw new ProductNotFoundException("product Not Found");} );
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {

        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product by id " + productId + " was not found"));

    }

    private Product updateExistingProduct(Product product, ProductUpdateRequest request) {
        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setInventory(request.getInventory());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        product.setCategory(category);
        return product;
    }




//done
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
//done
    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }
//done
    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }
//done
    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }
//done
    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }
//done
    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }
//done
    @Override
    public Long countProductsByBrandAndName(String Brand, String name) {
        return productRepository.countByBrandAndName(Brand, name);
    }


}
