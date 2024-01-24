
package com.gl.ceir.config.service.impl;

import com.gl.ceir.config.model.app.GenricResponse;
import com.gl.ceir.config.model.app.UploadFileDbDestination;
import com.gl.ceir.config.model.app.UploadedFileDB;
import com.gl.ceir.config.repository.app.UploadedFileDBRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

@Service
public class FileCopyServiceImpl {

    private static final Logger logger = LogManager.getLogger(FileCopyServiceImpl.class);

    @Autowired
    UploadedFileDBRepository uploadedFileDBRepository;

    public GenricResponse saveDetailsWithParam(UploadedFileDB uploadedFileDB) {
         StringJoiner sj = new StringJoiner(",");
        try {
            for (UploadFileDbDestination dest : uploadedFileDB.getDestination()) {
                uploadedFileDB.setDestServerName(dest.getDestServerName());
                uploadedFileDB.setDestFilePath(dest.getDestFilePath());
                uploadedFileDB.setId(null);
                logger.info("To String ::::::" + uploadedFileDB.toString());
                var uploadFile = uploadedFileDBRepository.save(uploadedFileDB);
                sj.add(uploadFile.getId().toString());
            }
            return new GenricResponse(0, "Success", sj.toString());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "::" + e + "::" + e.getCause().getCause().toString());
            return new GenricResponse(1, "Fail", e.getCause().getCause().toString());
        }
    }
}
