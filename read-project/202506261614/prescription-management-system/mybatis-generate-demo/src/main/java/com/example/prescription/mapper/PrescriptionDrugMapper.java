package com.example.prescription.mapper;

import com.example.prescription.entity.PrescriptionDrug;
import com.example.prescription.entity.PrescriptionDrugExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrescriptionDrugMapper {
    long countByExample(PrescriptionDrugExample example);

    int deleteByExample(PrescriptionDrugExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PrescriptionDrug row);

    int insertSelective(PrescriptionDrug row);

    List<PrescriptionDrug> selectByExample(PrescriptionDrugExample example);

    PrescriptionDrug selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") PrescriptionDrug row, @Param("example") PrescriptionDrugExample example);

    int updateByExample(@Param("row") PrescriptionDrug row, @Param("example") PrescriptionDrugExample example);

    int updateByPrimaryKeySelective(PrescriptionDrug row);

    int updateByPrimaryKey(PrescriptionDrug row);
}