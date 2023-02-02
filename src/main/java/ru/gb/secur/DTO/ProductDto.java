package ru.gb.secur.DTO;

import lombok.Data;
import ru.gb.secur.entities.Product;

@Data
public class ProductDto {

    private Long id;
    private String title;
    private Integer price;

    public ProductDto (Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
    }

//    public ProductDto(Object p) {
//    }
}
