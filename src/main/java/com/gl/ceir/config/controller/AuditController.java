/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.controller;

import com.gl.ceir.config.model.audit.AuditTrail;
import com.gl.ceir.config.model.audit.ModulesAuditTrail;
import com.gl.ceir.config.service.impl.ModulesAuditTrailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {

    @Autowired
    private ModulesAuditTrailService modulesAuditTrailService;

    @PostMapping("/moduleAuditServiceInsert")
    @ApiOperation(value = "insert in modules audit ", response = String.class)

    public MappingJacksonValue insertModuleAuditTable(@RequestBody ModulesAuditTrail ModulesAuditTrail) {
        return new MappingJacksonValue(modulesAuditTrailService.save(ModulesAuditTrail));
    }
    @PutMapping("/moduleAuditServiceUpdate")
    @ApiOperation(value = "update in modules audit ", response = String.class)
    public MappingJacksonValue updateModuleAuditTable(@RequestBody ModulesAuditTrail modulesAuditTrail) {
        return new MappingJacksonValue(modulesAuditTrailService.save(modulesAuditTrail));
    }

    @PostMapping("/auditTrailServiceInitialization")
    @ApiOperation(value = "save in audit trail db ", response = String.class)
    public MappingJacksonValue insertAuditTable(@RequestBody AuditTrail auditTrail) {
        return new MappingJacksonValue(modulesAuditTrailService.save(auditTrail));
    }

}
