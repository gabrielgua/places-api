package com.gabriel.clickbus.places.api.exception;

import com.gabriel.clickbus.places.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private StandardError.StandardErrorBuilder createStandardErrorBuilder(HttpStatus status, ErrorType type, String detail) {
        return StandardError.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }

    private List<StandardError.FieldError> fieldErrorList(BindingResult result) {
        return result.getAllErrors().stream()
                .map(error -> {
                   var name = error.getObjectName();
                   var message = error.getDefaultMessage();

                   if (error instanceof FieldError) {
                       name = ((FieldError) error).getField();
                   }

                   return StandardError.FieldError.builder()
                           .name(name)
                           .message(message)
                           .build();
                })
                .toList();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var detail = "One or more arguments are invalid.";
        var type = ErrorType.INVALID_DATA;

        var fieldList = fieldErrorList(ex.getBindingResult());
        var error = createStandardErrorBuilder(status, type, detail)
                .fields(fieldList)
                .build();

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var detail = ex.getMessage();
        var type = ErrorType.ENTITY_NOT_FOUND;

        var error = createStandardErrorBuilder(status, type, detail).build();

        return ResponseEntity.status(status).body(error);
    }
}
