package com.gl.ceir.config.model.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

@DynamicInsert
@Table(name = "file_to_sync")
@ToString
public class UploadedFileDB implements Serializable {

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("''")
    public String sourceServerName, sourceFilePath, sourceFileName, appName, serverName, remarks, txnId;

    @ApiModelProperty(hidden = true)
    @ColumnDefault("''")
    public String destServerName, destFilePath;

    public String getDestServerName() {
        return destServerName;
    }

    public void setDestServerName(String destServerName) {
        this.destServerName = destServerName;
    }

    public String getDestFilePath() {
        return destFilePath;
    }

    public void setDestFilePath(String destFilePath) {
        this.destFilePath = destFilePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceServerName() {
        return sourceServerName;
    }

    public void setSourceServerName(String sourceServerName) {
        this.sourceServerName = sourceServerName;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }


    @Transient
    public List<UploadFileDbDestination> destination;

    public List<UploadFileDbDestination> getDestination() {
        return destination;
    }

    public void setDestination(List<UploadFileDbDestination> destination) {
        this.destination = destination;
    }


    public UploadedFileDB(String sourceServerName, String sourceFilePath, String sourceFileName, String appName, String serverName, String remarks, String txnId, String destServerName, String destFilePath) {
        this.sourceServerName = sourceServerName;
        this.sourceFilePath = sourceFilePath;
        this.sourceFileName = sourceFileName;
        this.appName = appName;
        this.serverName = serverName;
        this.remarks = remarks;
        this.txnId = txnId;
        this.destServerName = destServerName;
        this.destFilePath = destFilePath;
    }
}
