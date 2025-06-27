package com.example.prescription.service.impl;

import com.example.prescription.entity.Drug;
import com.example.prescription.entity.DrugImage;
import com.example.prescription.mapper.DrugMapper;
import com.example.prescription.mapper.DrugImageMapper;
import com.example.prescription.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private DrugImageMapper drugImageMapper;

    @Override
    public boolean saveDrug(Drug drug) {
        return drugMapper.insertDrug(drug) > 0;
    }

    @Override
    public boolean updateDrug(Drug drug) {
        return drugMapper.updateDrugById(drug) > 0;
    }

    @Override
    public boolean deleteDrugCascade(Integer id) {
        drugImageMapper.deleteByDrugId(id);
        // 还可删除drug与prescription的关联
        return drugMapper.deleteDrugById(id) > 0;
    }

    @Override
    public Drug getDrugDetail(Integer id) {
        return drugMapper.selectDrugDetailById(id);
    }

    @Override
    public List<Drug> pageQueryDrug(int page, int size, String name) {
        int offset = (page - 1) * size;
        return drugMapper.pageQueryDrug(offset, size, name);
    }

    @Override
    public int countDrug(String name) {
        return drugMapper.countDrug(name);
    }

    @Override
    public boolean isDrugNameUnique(String name) {
        return drugMapper.countByDrugName(name) == 0;
    }

    @Override
    public boolean saveDrugImagePath(Integer drugId, String imagePath, String imageType) {
        DrugImage img = new DrugImage();
        img.setDrugId(drugId);
        img.setImagePath(imagePath);
        img.setImageType(imageType);
        img.setSortOrder(0);
        return drugImageMapper.insertDrugImage(img) > 0;
    }

    @Override
    public boolean deleteDrugImage(Integer imageId) {
        return drugImageMapper.deleteDrugImageById(imageId) > 0;
    }
}
        return drugImageMapper.deleteDrugImageById(imageId) > 0;
    }
}
