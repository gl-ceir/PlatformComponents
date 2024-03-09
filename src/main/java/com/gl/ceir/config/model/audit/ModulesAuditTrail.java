package com.gl.ceir.config.model.audit;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ModulesAuditTrail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int executionTime;
    private int statusCode;
    private String status;
    private String errorMessage;
    private String moduleName;
    private String featureName;
    private String info;
    private String serverName;
    private String action;
    private int failureCount;
    private int count;

//    @CreationTimestamp
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//    private LocalDateTime createdOn;

    @ApiModelProperty(hidden = true)
    private LocalDateTime modifiedOn;

    public ModulesAuditTrail() {
    }

}
