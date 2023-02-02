package ru.gb.secur.validators;

import org.springframework.stereotype.Component;
import ru.gb.secur.DTO.ProductDto;
import ru.gb.secur.exceptions.ValidationErrorException;

import java.util.ArrayList;
import java.util.List;
@Component
public class EntityValidator {

    private List<String> errorList = new ArrayList<>();

    public void checkProductDto (ProductDto productDto) {
        if(productDto.getTitle().isBlank() || productDto.getTitle().isEmpty()) {
            errorList.add(String.format("Title is not valid: " + productDto.getTitle()));
        }

        if(productDto.getPrice() <= 0) {
            errorList.add(String.format("Price is not valid: " + productDto.getPrice()));
        }

        if(!errorList.isEmpty()) {
            throw new ValidationErrorException(errorList);
        }
    }
}
