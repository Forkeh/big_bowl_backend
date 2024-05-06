package kea.exam.template.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorObject> handleEntityNotFoundException(EntityNotFoundException exception) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(LocalDate.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorObject);
    }
}
