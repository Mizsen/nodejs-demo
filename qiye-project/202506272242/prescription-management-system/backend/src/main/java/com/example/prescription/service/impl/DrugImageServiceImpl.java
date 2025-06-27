package com.example.prescription.service.impl;

import com.example.prescription.entity.DrugImage;
import com.example.prescription.mapper.DrugImageMapper;
import com.example.prescription.service.DrugImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugImageServiceImpl implements DrugImageService {

    @Autowired
    private DrugImageMapper drugImageMapper;

    @Override
    public boolean saveDrugImage(DrugImage drugImage) {
        return drugImageMapper.insertDrugImage(drugImage) > 0;
    }
}
