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
@TableName("drug")
// @ApiModel(value = "DrugEntity对象", description = "")
public class DrugEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("drug_name")
    private String drugName;

    @TableField("specification")
    private String specification;

    @TableField("manufacturer")
    private String manufacturer;

    @TableField("indications")
    private String indications;

    @TableField("created_time")
    private String createdTime;

    @TableField("created_by")
    private Integer createdBy;

    @TableField("updated_time")
    private String updatedTime;

    @TableField("updated_by")
    private Integer updatedBy;
}
