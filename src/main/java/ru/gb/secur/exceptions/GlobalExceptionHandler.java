package ru.gb.secur.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public ResponseEntity<NotFoundError> catchNotFoundException(NotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new NotFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ValidationError> catchValidationError(ValidationErrorException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ValidationError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
