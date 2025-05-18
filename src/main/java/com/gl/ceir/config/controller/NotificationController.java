/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.controller;

import com.gl.ceir.config.model.app.Notification;
import com.gl.ceir.config.model.app.UploadedFileDB;
import com.gl.ceir.config.repository.app.UploadedFileDBRepository;
import com.gl.ceir.config.service.impl.FileStorageService;
import com.gl.ceir.config.service.impl.NotificationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class NotificationController {

    @Value("${serverName}")
    private String serverName;

    @Value("${attachmentCopyServerName}")
    private String destServerName;

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    UploadedFileDBRepository uploadedFileDBRepository;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;
    private static final Logger logger = LogManager.getLogger(NotificationController.class);

    @ApiOperation(value = "Save all Notifications", response = String.class)
    @PostMapping("addNotifications")
    public MappingJacksonValue addNotifications(@RequestBody  Notification notification) {
        MappingJacksonValue mapping = new MappingJacksonValue(notificationServiceImpl.saveNotifications(notification));
        return mapping;
    }

    @ApiOperation(value = "uploadFile with Notification", response = String.class)
    @PostMapping("fileAttachmentNotification")
    public MappingJacksonValue addNotificationsWithFile(@RequestParam("file") MultipartFile file,
                                                        @RequestParam(required = true) String txnId,
                                                        @RequestParam(required = true) String email,
                                                        @RequestParam(required = true) String message,
                                                        @RequestParam(required = true) String msgLang,
                                                        @RequestParam(required = true) String subject) {

        Notification notif = new Notification();
        notif.setChannelType("EMAIL");
        notif.setEmail(email);
        notif.setMessage(message);
        notif.setSubject(subject);
        notif.setMsgLang(msgLang == null  || msgLang.isEmpty() ? "en" : msgLang.equalsIgnoreCase("kh") ? "kh" : "en");    // needs refactoring
        notif.setMsgLang(msgLang);
        notif.setFeatureTxnId(txnId);
        String filePath = fileStorageService.storeFile(notif, file);
        notif.setAttachment(filePath + "/" + file.getOriginalFilename());
        UploadedFileDB uploadedFileDB = new UploadedFileDB(serverName,
                filePath, file.getOriginalFilename(),
                "Email Attachment",
                "", "Email Attachment file to be synced",
                txnId, destServerName, filePath);
        logger.info(" Going to save data in file_to_sync :" + uploadedFileDB.toString());
        var uploadFile = uploadedFileDBRepository.save(uploadedFileDB);
        logger.info(" notification req:" + notif.toString());
        MappingJacksonValue mapping = new MappingJacksonValue(notificationServiceImpl.saveNotifications(notif));
        return mapping;
    }

//    @RequestMapping(value = "/uploadStuff", method = RequestMethod.POST)
//    public MappingJacksonValue doStuff(@RequestPart("json") @Valid Notification notification,
//                                              @RequestPart("file") MultipartFile file) {
//        String filePath = fileStorageService.storeFile(notification, file);
//        notification.setAttachment(filePath + "/" + file.getOriginalFilename());
//        logger.info(" notification req:" + notification.toString());
//        MappingJacksonValue mapping = new MappingJacksonValue(notificationServiceImpl.saveNotifications(notification));
//        return mapping;
//    }

}
