package com.example.prescription.mapper;

import com.example.prescription.entity.Drug;
import com.example.prescription.entity.DrugExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrugMapper {
    long countByExample(DrugExample example);

    int deleteByExample(DrugExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Drug row);

    int insertSelective(Drug row);

    List<Drug> selectByExample(DrugExample example);

    Drug selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Drug row, @Param("example") DrugExample example);

    int updateByExample(@Param("row") Drug row, @Param("example") DrugExample example);

    int updateByPrimaryKeySelective(Drug row);

    int updateByPrimaryKey(Drug row);
}