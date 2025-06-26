package com.example.prescription.mapper;

import com.example.prescription.entity.PrescriptionImage;
import com.example.prescription.entity.PrescriptionImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionImageMapper {
    long countByExample(PrescriptionImageExample example);

    int deleteByExample(PrescriptionImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PrescriptionImage row);

    int insertSelective(PrescriptionImage row);

    List<PrescriptionImage> selectByExample(PrescriptionImageExample example);

    PrescriptionImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") PrescriptionImage row, @Param("example") PrescriptionImageExample example);

    int updateByExample(@Param("row") PrescriptionImage row, @Param("example") PrescriptionImageExample example);

    int updateByPrimaryKeySelective(PrescriptionImage row);

    int updateByPrimaryKey(PrescriptionImage row);
}