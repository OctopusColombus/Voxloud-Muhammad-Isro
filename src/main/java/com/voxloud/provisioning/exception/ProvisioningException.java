package com.voxloud.provisioning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class ProvisioningException extends Exception {
    public ProvisioningException(String str) {
        super(str);
    }
}
