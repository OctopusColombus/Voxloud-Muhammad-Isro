package com.voxloud.provisioning.service;

import com.voxloud.provisioning.dto.ProvisioningResponse;

public interface ProvisioningService {

    ProvisioningResponse getProvisioningFile(String macAddress) throws Exception;
}
