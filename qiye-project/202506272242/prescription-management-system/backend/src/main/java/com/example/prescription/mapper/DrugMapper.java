package com.example.prescription.mapper;

import com.example.prescription.entity.Drug;
import java.util.List;

public interface DrugMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Drug row);

    Drug selectByPrimaryKey(Integer id);

    List<Drug> selectAll();

    int updateByPrimaryKey(Drug row);
}