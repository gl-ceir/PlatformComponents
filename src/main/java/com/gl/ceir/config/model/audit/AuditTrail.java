package com.gl.ceir.config.model.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class AuditTrail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ApiModelProperty(hidden = true)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdOn;

    private String browser, details;
    private Long featureId;
    private String featureName;

    private String jSessionId;

    @ApiModelProperty(hidden = true)
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedOn;
    private String roleType;
    private String publicIp;
    private String subFeature;
    @Column(length = 20)
    private String txnId;


    private Long userId;
    private String userName;
    private Long userTypeId;
    private String userType;


    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public AuditTrail() {
    }

    public AuditTrail(long userId, String userName, Long userTypeId, String userType, long featureId, String featureName, String subFeature, String jSessionId) {
        this.userId = userId;
        this.userName = userName;
        this.userTypeId = userTypeId;
        this.userType = userType;
        this.featureId = featureId;
        this.featureName = featureName;
        this.subFeature = subFeature;
        this.jSessionId = jSessionId;
    }

    public AuditTrail(long userId, String userName, Long userTypeId, String userType, long featureId,
                      String featureName, String subFeature, String jSessionId, String txnId) {
        this.userId = userId;
        this.userName = userName;
        this.userTypeId = userTypeId;
        this.userType = userType;
        this.featureId = featureId;
        this.featureName = featureName;
        this.subFeature = subFeature;
        this.jSessionId = jSessionId;
        this.txnId = txnId;
    }

    public AuditTrail(long userId, String userName, Long userTypeId, String userType, long featureId,
                      String featureName, String subFeature, String jSessionId, String txnId, String roleType, String publicIP, String browser) {
        this.userId = userId;
        this.userName = userName;
        this.userTypeId = userTypeId;
        this.userType = userType;
        this.featureId = featureId;
        this.featureName = featureName;
        this.subFeature = subFeature;
        this.jSessionId = jSessionId;
        this.txnId = txnId;
        this.roleType = roleType;
        this.publicIp = publicIP;
        this.browser = browser;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getSubFeature() {
        return subFeature;
    }

    public void setSubFeature(String subFeature) {
        this.subFeature = subFeature;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getjSessionId() {
        return jSessionId;
    }

    public void setjSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }


}
