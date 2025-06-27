package com.example.prescription.mapper;

import com.example.prescription.entity.DrugImage;
import com.example.prescription.entity.DrugImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrugImageMapper {
    long countByExample(DrugImageExample example);

    int deleteByExample(DrugImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DrugImage row);

    int insertSelective(DrugImage row);

    List<DrugImage> selectByExample(DrugImageExample example);

    DrugImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") DrugImage row, @Param("example") DrugImageExample example);

    int updateByExample(@Param("row") DrugImage row, @Param("example") DrugImageExample example);

    int updateByPrimaryKeySelective(DrugImage row);

    int updateByPrimaryKey(DrugImage row);
}