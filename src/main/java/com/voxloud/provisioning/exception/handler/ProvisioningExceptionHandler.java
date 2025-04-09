package com.voxloud.provisioning.exception.handler;

import com.voxloud.provisioning.dto.ErrorResponse;
import com.voxloud.provisioning.exception.ProvisioningException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProvisioningExceptionHandler {
    @ExceptionHandler(ProvisioningException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ProvisioningException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
