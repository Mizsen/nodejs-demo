package com.example.prescription.mapper;

import com.example.prescription.entity.Prescription;
import com.example.prescription.entity.PrescriptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionMapper {
    long countByExample(PrescriptionExample example);

    int deleteByExample(PrescriptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Prescription record);

    int insertSelective(Prescription record);

    List<Prescription> selectByExample(PrescriptionExample example);

    Prescription selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Prescription record, @Param("example") PrescriptionExample example);

    int updateByExample(@Param("record") Prescription record, @Param("example") PrescriptionExample example);

    int updateByPrimaryKeySelective(Prescription record);

    int updateByPrimaryKey(Prescription record);
}