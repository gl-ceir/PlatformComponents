/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.model.app;

import lombok.*;

/**
 * @author maverick
 */
@Getter
@Setter
@AllArgsConstructor
@ToString

public class AlertRequest {
    String alertId, alertMessage, alertProcess, priority, ip, serverName, featureName, remarks,txnId;
    int userId;
}

//public record Person (String name, String address) {}

