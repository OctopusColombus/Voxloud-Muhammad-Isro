package com.voxloud.provisioning.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "device")
@Entity
@Data
public class Device {

    @Id
    @Column(name = "mac_address")
    private String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "model")
    private DeviceModel model;

    @Column(name = "override_fragment")
    private String overrideFragment;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public enum DeviceModel {
        CONFERENCE,
        DESK
    }
}