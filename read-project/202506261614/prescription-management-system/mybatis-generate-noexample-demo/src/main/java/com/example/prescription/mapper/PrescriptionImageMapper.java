package com.example.prescription.mapper;

import com.example.prescription.entity.PrescriptionImage;
import java.util.List;

public interface PrescriptionImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrescriptionImage row);

    PrescriptionImage selectByPrimaryKey(Integer id);

    List<PrescriptionImage> selectAll();

    int updateByPrimaryKey(PrescriptionImage row);
}