package ru.gb.secur.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.secur.entities.Product;
import ru.gb.secur.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getAllProductsList() {
        return productRepository.findAll();
    }


    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct (Product product) {
        product.setId(null);
       return productRepository.save(product);
    }

    //проверить, не уверен что апдейт делают через save
    public Product updateProduct (Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
