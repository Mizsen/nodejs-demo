package com.example.prescription.mapper;

import com.example.prescription.entity.PrescriptionImage;
import com.example.prescription.entity.PrescriptionImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionImageMapper {
    long countByExample(PrescriptionImageExample example);

    int deleteByExample(PrescriptionImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PrescriptionImage record);

    int insertSelective(PrescriptionImage record);

    List<PrescriptionImage> selectByExample(PrescriptionImageExample example);

    PrescriptionImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PrescriptionImage record, @Param("example") PrescriptionImageExample example);

    int updateByExample(@Param("record") PrescriptionImage record, @Param("example") PrescriptionImageExample example);

    int updateByPrimaryKeySelective(PrescriptionImage record);

    int updateByPrimaryKey(PrescriptionImage record);
}