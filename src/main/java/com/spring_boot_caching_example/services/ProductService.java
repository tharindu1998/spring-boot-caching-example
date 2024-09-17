package com.spring_boot_caching_example.services;
import com.spring_boot_caching_example.classes.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        // Simulate a slow database query
        try {
            Thread.sleep(3000); // Simulates a delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Product(id, "Sample Product");
    }

    //Conditional Caching example

    @Cacheable(value = "products", key = "#id", condition = "#id > 10")
    public Product getProductByIdConditionalMethod(Long id) {
        try {
            Thread.sleep(3000); // Simulates a delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Product(id, "Sample Product");
    }

    //Updating and Removing Caches with @CachePut and @CacheEvict

    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        // Update product logic
        return product;
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        // Delete product logic
    }

    @CacheEvict(value = "products", allEntries = true)
    public void clearCache() {
        // Logic to clear cache
    }

}