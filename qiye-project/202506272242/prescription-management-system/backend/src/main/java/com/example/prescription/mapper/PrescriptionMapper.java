package com.example.prescription.mapper;

import com.example.prescription.entity.Prescription;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PrescriptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Prescription row);

    Prescription selectByPrimaryKey(Integer id);

    List<Prescription> selectAll();

    int updateByPrimaryKey(Prescription prescription);

    List<Prescription> pageQuery(int offset, int size, String name, String indications);

    int count(String name, String indications);

    Prescription selectDetailById(Integer id);

    int deleteCascade(List<Integer> ids);
}