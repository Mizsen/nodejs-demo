package com.example.prescription.mapper;

import com.example.prescription.entity.DrugImage;
import org.apache.ibatis.annotations.Mapper;


public interface DrugImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DrugImage row);

    DrugImage selectByPrimaryKey(Integer id);

    List<DrugImage> selectAll();

    int updateByPrimaryKey(DrugImage row);

    int insertDrugImage(DrugImage drugImage);
}