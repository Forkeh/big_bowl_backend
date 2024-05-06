package kea.exam.template.exceptions;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private LocalDate timestamp;
}
