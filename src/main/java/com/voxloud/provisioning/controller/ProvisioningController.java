package com.voxloud.provisioning.controller;

import com.voxloud.provisioning.dto.ProvisioningResponse;
import com.voxloud.provisioning.service.ProvisioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {

    @Autowired
    private ProvisioningService provisioningService;

    @GetMapping("/provisioning/{macAddress}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ProvisioningResponse getProvisioningFile(@PathVariable("macAddress") String macAddress) throws Exception {
        return provisioningService.getProvisioningFile(macAddress);
    }
}