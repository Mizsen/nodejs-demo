package com.example.prescription.mapper;

import com.example.prescription.entity.Drug;
import com.example.prescription.entity.DrugExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrugMapper {
    long countByExample(DrugExample example);

    int deleteByExample(DrugExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Drug record);

    int insertSelective(Drug record);

    List<Drug> selectByExample(DrugExample example);

    Drug selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Drug record, @Param("example") DrugExample example);

    int updateByExample(@Param("record") Drug record, @Param("example") DrugExample example);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKey(Drug record);
}