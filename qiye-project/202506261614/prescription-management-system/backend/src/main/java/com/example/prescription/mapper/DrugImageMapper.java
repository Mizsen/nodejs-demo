package com.example.prescription.mapper;

import com.example.prescription.entity.DrugImage;
import java.util.List;

public interface DrugImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DrugImage row);

    DrugImage selectByPrimaryKey(Integer id);

    List<DrugImage> selectAll();

    int updateByPrimaryKey(DrugImage row);
}