package com.example.prescription.mapper;

import com.example.prescription.entity.DrugImage;
import com.example.prescription.entity.DrugImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrugImageMapper {
    long countByExample(DrugImageExample example);

    int deleteByExample(DrugImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DrugImage record);

    int insertSelective(DrugImage record);

    List<DrugImage> selectByExample(DrugImageExample example);

    DrugImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DrugImage record, @Param("example") DrugImageExample example);

    int updateByExample(@Param("record") DrugImage record, @Param("example") DrugImageExample example);

    int updateByPrimaryKeySelective(DrugImage record);

    int updateByPrimaryKey(DrugImage record);
}