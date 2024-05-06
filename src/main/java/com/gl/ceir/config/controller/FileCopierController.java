/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.controller;

import com.gl.ceir.config.service.impl.FileCopyServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.app.UploadedFileDB;
 
@RestController
public class FileCopierController {

    @Autowired
    private FileCopyServiceImpl fileCopyServiceImpl;

    @PostMapping("/fileCopyApi")
    public MappingJacksonValue saveFileCopyDetails(@RequestBody UploadedFileDB uploadedFileDB) {
        return new MappingJacksonValue(fileCopyServiceImpl.saveDetailsWithParam(uploadedFileDB));
    }
}
