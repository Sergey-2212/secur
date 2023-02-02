package ru.gb.secur.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.secur.DTO.ProductDto;
import ru.gb.secur.entities.Product;
import ru.gb.secur.entityconverters.ProductConverter;
import ru.gb.secur.exceptions.NotFoundException;
import ru.gb.secur.services.ProductService;
import ru.gb.secur.validators.EntityValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class MainController {

    private final ProductService service;
    private final ProductConverter productConverter;
    private final EntityValidator entityValidator;

    @GetMapping("/")
    public List<ProductDto> getAllProductList () {
        return service.getAllProductsList().stream().map(product -> new ProductDto(product)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return service.getProductById(id)
                .map(p -> new ProductDto(p)).orElseThrow(() -> new NotFoundException(String.format("Product not found by id: " + id)));
    }

    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductDto addNewProduct(@RequestBody ProductDto productDto) {
        entityValidator.checkProductDto(productDto);
        Product product = productConverter.dtoToEntityConverter(productDto);
       return productConverter.entityToDtoConverter(service.addProduct(product));
    }

    @PutMapping("/")
    @ResponseStatus(code = HttpStatus.FOUND)
    public ProductDto updateProduct (@RequestBody ProductDto productDto) {
        entityValidator.checkProductDto(productDto);
        Product product = productConverter.dtoToEntityConverter(productDto);
        return productConverter.entityToDtoConverter(service.updateProduct(product));
    }
    @DeleteMapping("/id/")
    public void deleteProductById(@RequestParam Long id) {
        service.deleteProductById(id);
    }

}
