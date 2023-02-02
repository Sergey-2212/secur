package ru.gb.secur.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.secur.DTO.ProductDto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "date_of_adding")
    private Timestamp datOfAdding;

    public Product(ProductDto productDto) {
        id = productDto.getId();
        title = productDto.getTitle();
        price = productDto.getPrice();
        //datOfAdding = new Timestamp(System.currentTimeMillis());
    }
}
