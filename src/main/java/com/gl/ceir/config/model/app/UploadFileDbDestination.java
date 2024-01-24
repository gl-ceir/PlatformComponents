package com.gl.ceir.config.model.app;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UploadFileDbDestination {

    String destServerName;
    String destFilePath;

    public UploadFileDbDestination(String destServerName, String destFilePath) {
        this.destServerName = destServerName;
        this.destFilePath = destFilePath;
    }

    public UploadFileDbDestination(){}
}
