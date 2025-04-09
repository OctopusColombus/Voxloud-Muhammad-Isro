package com.voxloud.provisioning.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxloud.provisioning.dto.OverrideFragment;
import com.voxloud.provisioning.dto.ProvisioningResponse;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.exception.ProvisioningException;
import com.voxloud.provisioning.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${provisioning.domain}")
    private String provisioningDomain;

    @Value("${provisioning.port}")
    private String provisioningPort;

    @Value("${provisioning.codecs}")
    private List<String> provisioningCodecs;

    public ProvisioningResponse getProvisioningFile(String macAddress) throws Exception {
        ProvisioningResponse response = new ProvisioningResponse();
        Device device = deviceRepository.findByMacAddress(macAddress);

        if (device == null) {
            throw new ProvisioningException("DEVICE_NOT_FOUND");
        }

        response.setUsername(device.getUsername());
        response.setPassword(device.getPassword());
        response.setCodecs(provisioningCodecs);

        //if override fragment is not null then convert to json and read to object
        if (device.getOverrideFragment() != null) {
            OverrideFragment overrideFragment;
            try {
                overrideFragment = objectMapper.readValue(device.getOverrideFragment(), OverrideFragment.class);
            } catch (Exception e) {
                String fragment = device.getOverrideFragment().replace("=", "\":\"");
                fragment = fragment.replace("\n", "\",\"");
                fragment = "{\"".concat(fragment).concat("\"}");
                overrideFragment = objectMapper.readValue(fragment, OverrideFragment.class);
            }

            response.setPort(overrideFragment.getPort());
            response.setDomain(overrideFragment.getDomain());
            response.setTimeout(overrideFragment.getTimeout());
        } else {
            response.setPort(provisioningPort);
            response.setDomain(provisioningDomain);
        }

        return response;
    }
}
