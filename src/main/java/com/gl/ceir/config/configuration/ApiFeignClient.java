//package com.gl.ceir.config.configuration;
//
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//
//
//@Service
// // @FeignClient(name = "apiFeignClient", url = "http://159.223.159.153:9509/ceir/")
//// @Service
//public interface ApiFeignClient {
//    @PostMapping(value = "/fileAttachmentNotification", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String uploadMultipart(@RequestPart("file") MultipartFile file,
//                           @RequestParam String email,
//                           @RequestParam String subject,
//                           @RequestParam String message,
//                           @RequestParam String msgLang,
//                           @RequestParam String txnId);
//
//    @PostMapping(value = "/fileAttachmentNotification", consumes = "multipart/form-data")
//    String uploadFile(@RequestPart("file") File file,
//                      @RequestParam String email,
//                      @RequestParam String subject,
//                      @RequestParam String message,
//                      @RequestParam String msgLang,
//                      @RequestParam String txnId);
//
////    @PostMapping("fileAttachmentNotification")
////    void upload(@RequestPart(name = "file") MultipartFile file,
////                @RequestParam String email,
////                @RequestParam String subject,
////                @RequestParam String message,
////                @RequestParam String msgLang,
////                @RequestParam String txnId);
//}
//
////@FeignClient(name="attachment-service", fallback=AttachmentHystrixFallback.class)
//
//
