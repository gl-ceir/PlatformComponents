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
    private Integer status;
    private String priority;
    private String ip;
    private String serverName;
    private String featureName;
    private String remarks;
    private String txnId;
    private Integer userId;


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

    public RunningAlertDb(Integer userId, String alertId, String description, Integer status) {
        this.userId = userId;
        this.alertId = alertId;
        this.description = description;
        this.status = status;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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


    @Override
    public String toString() {
        return "RunningAlertDb{" +
                "id=" + id +
                ", alertId='" + alertId + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority='" + priority + '\'' +
                ", ip='" + ip + '\'' +
                ", serverName='" + serverName + '\'' +
                ", featureName='" + featureName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", txnId='" + txnId + '\'' +
                ", userId=" + userId +
                ", alertMessage='" + alertMessage + '\'' +
                ", alertProcess='" + alertProcess + '\'' +
                '}';
    }
}
