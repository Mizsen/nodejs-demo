package com.example.prescription.service;

import com.example.prescription.entity.Prescription;
import java.util.List;

public interface PrescriptionService {
    boolean savePrescriptionWithRelations(Prescription prescription);
    List<Prescription> pageQuery(int page, int size, String name, String indications);
    int count(String name, String indications);
    Prescription getPrescriptionDetail(Integer id);
    boolean updatePrescriptionWithRelations(Prescription prescription);
    boolean deletePrescriptionsCascade(List<Integer> ids);
    boolean savePrescriptionImagePath(Integer prescriptionId, String imagePath, String imageType);
}