package com.example.prescription.mapper;

import com.example.prescription.entity.Prescription;
import com.example.prescription.entity.PrescriptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionMapper {
    long countByExample(PrescriptionExample example);

    int deleteByExample(PrescriptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Prescription row);

    int insertSelective(Prescription row);

    List<Prescription> selectByExample(PrescriptionExample example);

    Prescription selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Prescription row, @Param("example") PrescriptionExample example);

    int updateByExample(@Param("row") Prescription row, @Param("example") PrescriptionExample example);

    int updateByPrimaryKeySelective(Prescription row);

    int updateByPrimaryKey(Prescription row);
}