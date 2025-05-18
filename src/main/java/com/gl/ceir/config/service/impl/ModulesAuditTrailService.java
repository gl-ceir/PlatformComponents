/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.service.impl;

import com.gl.ceir.config.exceptions.InternalServicesException;
import com.gl.ceir.config.model.app.GenricResponse;
import com.gl.ceir.config.model.audit.AuditTrail;
import com.gl.ceir.config.model.audit.ModulesAuditTrail;
import com.gl.ceir.config.repository.audit.AuditTrailRepository;
import com.gl.ceir.config.repository.audit.ModulesAuditTrailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ModulesAuditTrailService {

    @Autowired
    ModulesAuditTrailRepository modulesAuditTrailRepository;

    @Autowired
    AuditTrailRepository auditTrailRepository;


    private final Logger logger = LogManager.getLogger(getClass());

    public GenricResponse save(ModulesAuditTrail modulesAuditTrail) {
        try {
            modulesAuditTrail.setModifiedOn(LocalDateTime.now());
            var response =modulesAuditTrailRepository.save(modulesAuditTrail);
            return new GenricResponse(0, "Success", String.valueOf(response.getId()));
        } catch (Exception e) {
            logger.error("Error occurs when saving data in db  " + e.getLocalizedMessage());
            throw new InternalServicesException(this.getClass().getName(), "internal server error" + e.getLocalizedMessage() );
        }
    }


    public Object save(AuditTrail auditTrail) {
        try {
            var response =auditTrailRepository.save(auditTrail);
            return new GenricResponse(0, "Success", String.valueOf(response.getId()));
        } catch (Exception e) {
            logger.error("Error occurs when saving data in db  " + e.getLocalizedMessage());
            throw new InternalServicesException(this.getClass().getName(), "internal server error" + e.getLocalizedMessage() );
        }
    }

}
