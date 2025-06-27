package com.example.prescription.service;

import com.example.prescription.entity.Drug;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DrugService {
    boolean saveDrug(Drug drug);

    boolean updateDrug(Drug drug);

    boolean deleteDrugCascade(Integer id);

    Drug getDrugDetail(Integer id);

    List<Drug> pageQueryDrug(int page, int size, String name);

    int countDrug(String name);

    boolean isDrugNameUnique(String name);

    boolean saveDrugImage(Integer drugId, MultipartFile file, String imageType);

    boolean deleteDrugImage(Integer imageId);

    boolean saveDrugImagePath(Integer drugId, String imagePath, String imageType);
}