package com.example.prescription.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
// import io.swagger.annotations.ApiModel;
// import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 你的姓名
 * @since 2025-06-24
 */
@Getter
@Setter
@TableName("prescription")
// @ApiModel(value = "PrescriptionEntity对象", description = "")
public class PrescriptionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("prescription_name")
    private String prescriptionName;

    @TableField("indications")
    private String indications;

    @TableField("usage")
    private String usage;

    @TableField("treatment_cycle")
    private String treatmentCycle;

    @TableField("created_time")
    private String createdTime;

    @TableField("created_by")
    private Integer createdBy;

    @TableField("updated_time")
    private String updatedTime;

    @TableField("updated_by")
    private Integer updatedBy;
}
