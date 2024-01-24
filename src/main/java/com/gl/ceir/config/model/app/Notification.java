package com.gl.ceir.config.model.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@DynamicInsert
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channelType;

    private String message;

    private Integer userId;

    private Integer featureId;

    private String featureTxnId;

    private String featureName;

    private String subFeature;

    private Integer status;

    private String subject;

    private Integer retryCount;

    private String referTable;

    private String roleType;

    private String receiverUserType;

    private String email;

    private String msisdn;

    private String operatorName;



    private String msgLang;

    private Integer deliveryStatus;

    private String sendSmsInterface;

    private Integer checkImeiId;



    public Notification() {
    }

    
}
