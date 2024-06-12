package com.gl.ceir.config.model.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "sys_generated_alert")
public class RunningAlertDb implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String alertId;
    private String description;
    // private Integer status;
    private String priority;
    private String ip;
    private String serverName;
    private String featureName;
    private String remarks;
    private String txnId;
    private String username;


//    @CreationTimestamp
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime createdOn;
//
//    @UpdateTimestamp
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime modifiedOn;


    public RunningAlertDb(){}

    @Transient
    private String alertMessage, alertProcess;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
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



    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getAlertProcess() {
        return alertProcess;
    }

    public void setAlertProcess(String alertProcess) {
        this.alertProcess = alertProcess;
    }

    public RunningAlertDb(String description, String alertId) {
        this.description = description;
        this.alertId = alertId;
    }

    public RunningAlertDb(String alertId, String alertMessage, String alertProcess, String description, String featureName, String ip, String priority, String remarks, String serverName, String txnId,String username) {
        this.alertId = alertId;
        this.alertMessage = alertMessage;
        this.alertProcess = alertProcess;
        this.description = description;
        this.featureName = featureName;
        this.ip = ip;
        this.priority = priority;
        this.remarks = remarks;
        this.serverName = serverName;
        this.txnId = txnId;
        this.username=username;
    }


    @Override
    public String toString() {
        return "RunningAlertDb{" +
                "alertId='" + alertId + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", ip='" + ip + '\'' +
                ", serverName='" + serverName + '\'' +
                ", featureName='" + featureName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", txnId='" + txnId + '\'' +
                ", username='" + username + '\'' +
                ", alertMessage='" + alertMessage + '\'' +
                ", alertProcess='" + alertProcess + '\'' +
                '}';
    }

}
