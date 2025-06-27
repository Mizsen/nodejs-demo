package com.example.prescription.service.impl;

import com.example.prescription.entity.Prescription;
import com.example.prescription.mapper.PrescriptionMapper;
import com.example.prescription.service.PrescriptionService;
import com.example.prescription.mapper.PrescriptionImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Autowired
    private PrescriptionImageMapper prescriptionImageMapper;

    @Override
    public boolean savePrescriptionWithRelations(Prescription prescription) {
        // 保存药方、药品关联、图片等
        return prescriptionMapper.insert(prescription) > 0;
    }

    @Override
    public List<Prescription> pageQuery(int page, int size, String name, String indications) {
        int offset = (page - 1) * size;
        return prescriptionMapper.pageQuery(offset, size, name, indications);
    }

    @Override
    public int count(String name, String indications) {
        return prescriptionMapper.count(name, indications);
    }

    @Override
    public Prescription getPrescriptionDetail(Integer id) {
        return prescriptionMapper.selectDetailById(id);
    }

    @Override
    public boolean updatePrescriptionWithRelations(Prescription prescription) {
        // 更新药方、药品关联、图片等
        return prescriptionMapper.updateByPrimaryKey(prescription) > 0;
    }

    @Override
    public boolean deletePrescriptionsCascade(List<Integer> ids) {
        // 级联删除药品关联、图片等
        return prescriptionMapper.deleteCascade(ids) > 0;
    }

    @Override
    public boolean savePrescriptionImagePath(Integer prescriptionId, String imagePath, String imageType) {
        com.example.prescription.entity.PrescriptionImage img = new com.example.prescription.entity.PrescriptionImage();
        img.setPrescriptionId(prescriptionId);
        img.setImagePath(imagePath);
        img.setImageType(imageType);
        img.setSortOrder(0);
        return prescriptionImageMapper.insertPrescriptionImage(img) > 0;
    }
}
