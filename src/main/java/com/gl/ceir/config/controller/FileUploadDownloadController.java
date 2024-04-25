/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.ceir.config.controller;

// import com.gl.ceir.config.configuration.ConnectionConfiguration;

import com.gl.ceir.config.model.app.FileUploadDownloadResponse;
import com.gl.ceir.config.service.impl.ModulesAuditTrailService;
import com.gl.ceir.config.service.impl.FileStorageService;
 import com.gl.ceir.config.util.VirtualIpAddressUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
// @RequestMapping("/sampleFile")
public class FileUploadDownloadController {

    @Autowired
    private FileStorageService fileStorageService;

 
    @Autowired
    ModulesAuditTrailService modulesAuditTrailService;

    @Autowired
    VirtualIpAddressUtil virtualIpAddressUtil;

    // getVirtualIpDetails getVirtualIpDetails

    private static final Logger logger = LogManager.getLogger(FileUploadDownloadController.class);

  //  @PostMapping("/uploadFile")
    public FileUploadDownloadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String filePath  = fileStorageService.storeFile(null,file);
        logger.info("fileName:" + filePath);
       // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
        return new FileUploadDownloadResponse(file.getOriginalFilename(), filePath+"/"+file.getOriginalFilename(), file.getContentType(), file.getSize());
    }

 //   @PostMapping("/uploadMultipleFiles")
    public List<FileUploadDownloadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        logger.info("Upload multiple File " + files.length);

        var response =
                Arrays.stream(files)
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());

        logger.info("Upload multiple response " + response.toString());
        return response;
    }

    //    Downlaoder
 //   @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
 

 //   @GetMapping("/getVirtualIpDetails")
    public MappingJacksonValue getVirtualIpDetails() {
        logger.info("Going for vip :");
        String vvip = virtualIpAddressUtil.getVirtualIp();
        logger.info("Going to Full list  = " + vvip);
        virtualIpAddressUtil.getFullList();

        logger.info("Exiting the controller = ");

        return new MappingJacksonValue("");
    }


  //  @GetMapping("/testControllerForEngine")
    public MappingJacksonValue testControllerForEngine(@RequestParam("user") String user, @RequestParam("pass") String pass) {

//                RuleInfo re = new RuleInfo("appdbName", "auddbName", "repdbName", "TAC_FORMAT", "executeRule", "NONCDR", "IMEIESNMEID",
//                "SNofDevice", "file_name",
//                "DeviceType", "operator", "DeviceIdType", "operator_tag", "MSISDN",
//                "action",
//                "", "", "", "operator",
//                "", "", "txn_id", "fileArray", "period", null, null);

        //   RuleEngineApplication.startRuleEngine(re);

        logger.info("Going to raise  = ");
        return new MappingJacksonValue("");
    }


 
}


//    public String getResult(String user_type, String feature, String imei, Long imei_type) {
//        String rulePass = "true";
//
//        try {
//            Connection conn = getSQlConnection();
//            BufferedWriter bw = null;
//            String expOutput = "";
//            // ArrayList<Rule> rule_details = new ArrayList<Rule>();
//            int i = imei_type.intValue();
//            String deviceIdValue = null;
//            switch (i) {
//                case 0:
//                    deviceIdValue = "IMEI";
//                    break;
//                case 1:
//                    deviceIdValue = "MEID";
//                    break;
//                case 2:
//                    deviceIdValue = "ESN";
//                    break;
//            }
//            // deviceIdValue = "IMEI"; // to be remove if another values came ,
//            List<RuleEngineMapping> ruleList
//                    = checkImeiRepository.getByFeatureAndUserTypeOrderByRuleOrder(feature, user_type);
//            logger.info("RuleList is " + ruleList);
//            for (RuleEngineMapping cim : ruleList) {
//                Rule rule = new Rule(cim.getName(), cim.getOutput(), cim.getRuleMessage());
//                // rule_details.add(rule);
//                // }
//                // logger.info("Rules Populated"); // optimse
//                // for (Rule rule : rule_details) {
//
//                String[] my_arr = {
//                    rule.rule_name,
//                    "1",
//                    "NONCDR",
//                    ((rule.rule_name.equals("IMEI_LUHN_CHECK") || rule.rule_name.equals("IMEI_LENGTH"))
//                    ? imei
//                    : imei.substring(0, 14)),
//                    "4",
//                    "5",
//                    "6",
//                    "7",
//                    "8",
//                    deviceIdValue,
//                    "",
//                    " ",
//                    " ",
//                    ""
//                };
//                logger.info("Rule : " + rule.rule_name);
//                expOutput = RuleEngineApplication.startRuleEngine(my_arr, conn, bw);
//                logger.info("Rule Output By Engine :" + expOutput + " , Expected Output : " + rule.output);
//                if (rule.output.equalsIgnoreCase(expOutput)) { // go to next rule( rule_engine _mapping )
//                    logger.info("Rule Passed");
//                } else {
//                    logger.info("Rule failed at " + rule.rule_name);
//                    rulePass = rule.rule_name;
//                    break;
//                }
//            }
//            logger.info("Conn Close");
//            conn.close();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
//        } finally {
//
//            // auditTrailRepository.save(new AuditTrail(checkImeiValuesEntity.getUserId(),
//            // checkImeiValuesEntity.getUserName(),
//            // Long.valueOf(checkImeiValuesEntity.getUserTypeId()),
//            // checkImeiValuesEntity.getUserType(),
//            // Long.valueOf(checkImeiValuesEntity.getFeatureId()), Features.CONSIGNMENT,
//            // "VIEW_ALL", "", "NA",
//            // checkImeiValuesEntity.getRoleType(), checkImeiValuesEntity.getPublicIp(),
//            // checkImeiValuesEntity.getBrowser()));
//            // logger.info("AUDIT : Saved view request in audit.");
//        }
//        return rulePass;
//    }
//    private Connection getSQlConnection() throws SQLException {
//        Connection c1 = null;
//        StandardServiceRegistry standardRegistry
//                = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//
//        Metadata metadata
//                = new MetadataSources(standardRegistry)
//                        .addAnnotatedClass(CheckImeiServiceImpl.class)
//                        .buildMetadata();
//
//        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//
//        try {
//            c1 = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
//            logger.info("Connection for Rule " + c1);
//            logger.info(c1.getMetaData().getDatabaseProductName());
//
//        } catch (Exception e) {
//            logger.info(" Erorr " + e);
//        }
//        return c1;
//    }
//            if (checkImeiRequest.getChannel().equalsIgnoreCase("ussd") || checkImeiRequest.getChannel().equalsIgnoreCase("sms")) {
//                message = message.replace("$brandName", gsmaTacDetails.getBrand_name())
//                        .replace("$modelName", gsmaTacDetails.getModel_name())
//                        .replace("$deviceType", gsmaTacDetails.getDevice_type())
//                        .replace("$manufacturer", gsmaTacDetails.getManufacturer())
//                        .replace("$marketingName", gsmaTacDetails.getMarketing_name());
//            }
