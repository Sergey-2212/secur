package ru.gb.secur.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationErrorException extends RuntimeException {
    public ValidationErrorException(List<String> errorList) {
        super(errorList.stream().collect(Collectors.joining(", ")));
    }
}
