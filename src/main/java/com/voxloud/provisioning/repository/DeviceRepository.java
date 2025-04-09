package com.voxloud.provisioning.repository;

import com.voxloud.provisioning.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findByMacAddress(String macAddress);
}
