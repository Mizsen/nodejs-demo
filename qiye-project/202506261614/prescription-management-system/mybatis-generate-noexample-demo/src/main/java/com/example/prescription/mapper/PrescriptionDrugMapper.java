package com.example.prescription.mapper;

import com.example.prescription.entity.PrescriptionDrug;
import java.util.List;

public interface PrescriptionDrugMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrescriptionDrug row);

    PrescriptionDrug selectByPrimaryKey(Integer id);

    List<PrescriptionDrug> selectAll();

    int updateByPrimaryKey(PrescriptionDrug row);
}