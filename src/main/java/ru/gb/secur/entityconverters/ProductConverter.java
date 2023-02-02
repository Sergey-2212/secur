package ru.gb.secur.entityconverters;

import org.springframework.stereotype.Component;
import ru.gb.secur.DTO.ProductDto;
import ru.gb.secur.entities.Product;

@Component
public class ProductConverter {

    public Product dtoToEntityConverter (ProductDto productDto) {
        return new Product(productDto);
    }

    public ProductDto entityToDtoConverter(Product product) {
        return new ProductDto(product);
    }
}
