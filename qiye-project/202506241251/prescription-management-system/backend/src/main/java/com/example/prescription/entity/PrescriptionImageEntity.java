package com.example.prescription.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("prescription_image")
@ApiModel(value = "PrescriptionImageEntity对象", description = "")
public class PrescriptionImageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("prescription_id")
    private Integer prescriptionId;

    @TableField("image_path")
    private String imagePath;

    @TableField("image_type")
    private String imageType;

    @TableField("sort_order")
    private Integer sortOrder;

    @TableField("created_time")
    private String createdTime;
}
