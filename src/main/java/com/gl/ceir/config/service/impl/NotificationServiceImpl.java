/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.service.impl;

import com.gl.ceir.config.model.app.GenricResponse;
import com.gl.ceir.config.model.app.Notification;
import com.gl.ceir.config.repository.app.NotificationRepository;
import com.gl.ceir.config.repository.app.OperatorSeriesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author maverick
 */
@Service
public class NotificationServiceImpl {

    private static final Logger logger = LogManager.getLogger(NotificationServiceImpl.class);

    @Autowired
    AlertServiceImpl alertServiceImpl;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    OperatorSeriesRepository operatorSeriesRepository;

    public Object saveNotifications(Notification notification) {
        try {
            notification.setOperatorName(getActualOperator(notification));
            var value = notificationRepository.save(notification);
            return new GenricResponse(0, "Success", String.valueOf(value.getId()));
        } catch (Exception e) {
            logger.error(" Exception occurred " + e + "::::::" + e.getLocalizedMessage() + "::::::" + e.getMessage());
            alertServiceImpl.raiseAlertById("alert_1108");
            return new GenricResponse(1, "Fail", e.getCause().getCause().toString());
        }
    }

    private String getActualOperator(Notification notification) {
        try {
            String msisdn = notification.getMsisdn().substring(0, 5);
            logger.info("Actual msisdn  is " + msisdn);
            int series = Integer.parseInt(msisdn.substring(0, 5));
            var value = operatorSeriesRepository.findBySeriesStartLessThanEqualAndSeriesEndGreaterThanEqual(series, series);
            logger.info(" Actual result  " + value);
            return value.getOperatorName();
        } catch (Exception e) {
            logger.warn("No operator Found for msisdn: jdbc Response :" + e);
            return "";
        }
    }
}
// var value = operatorSeriesRepository.getBySeriesStartAndSeriesType(Integer.parseInt(msisdn.substring(0, 5)), "msisdn").getOperatorName();
