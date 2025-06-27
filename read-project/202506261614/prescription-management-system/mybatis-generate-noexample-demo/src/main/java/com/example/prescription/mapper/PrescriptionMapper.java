package com.example.prescription.mapper;

import com.example.prescription.entity.Prescription;
import java.util.List;

public interface PrescriptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Prescription row);

    Prescription selectByPrimaryKey(Integer id);

    List<Prescription> selectAll();

    int updateByPrimaryKey(Prescription row);
}