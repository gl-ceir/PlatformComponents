/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.controller;

import com.gl.ceir.config.model.app.RunningAlertDb;
import com.gl.ceir.config.service.impl.AlertServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AlertController {

    @Autowired
    private AlertServiceImpl alertServiceImpl;
    @ApiOperation(value = "Raise Alert by id", response = String.class)
    @RequestMapping(path = "/alert/{id}", method = RequestMethod.GET)
    public MappingJacksonValue raiseAlertById(@PathVariable(value = "id") String id) {
        return new MappingJacksonValue(alertServiceImpl.raiseAlertById(id));
    }

    @PostMapping("/alert")
    @ApiOperation(value = "Raise Alert ", response = String.class)
    public MappingJacksonValue save(@RequestBody Map<String, String> data) {
        return new MappingJacksonValue(alertServiceImpl.saveAlertWithParam(data));
    }
}
