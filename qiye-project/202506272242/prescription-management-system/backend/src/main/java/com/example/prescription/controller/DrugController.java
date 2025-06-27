package com.example.prescription.controller;

import com.example.prescription.entity.Drug;
import com.example.prescription.entity.DrugImage;
import com.example.prescription.service.DrugService;
import com.example.prescription.service.DrugImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 你的姓名
 * @since 2025-06-24
 */
@RestController
@RequestMapping("/api/drugs")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @Autowired
    private DrugImageService drugImageService;

    // 新增药品
    @PostMapping
    public Map<String, Object> addDrug(@RequestBody Drug drug) {
        Map<String, Object> result = new HashMap<>();
        if (!drugService.isDrugNameUnique(drug.getDrugName())) {
            result.put("success", false);
            result.put("msg", "药品名称已存在");
            return result;
        }
        boolean ok = drugService.saveDrug(drug);
        result.put("success", ok);
        result.put("msg", ok ? "新增成功" : "新增失败");
        return result;
    }

    // 修改药品
    @PutMapping("/{id}")
    public Map<String, Object> updateDrug(@PathVariable Integer id, @RequestBody Drug drug) {
        drug.setId(id);
        Map<String, Object> result = new HashMap<>();
        boolean ok = drugService.updateDrug(drug);
        result.put("success", ok);
        result.put("msg", ok ? "修改成功" : "修改失败");
        return result;
    }

    // 删除药品
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteDrug(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean ok = drugService.deleteDrugCascade(id);
        result.put("success", ok);
        result.put("msg", ok ? "删除成功" : "删除失败");
        return result;
    }

    // 查询药品详情
    @GetMapping("/{id}")
    public Drug getDrug(@PathVariable Integer id) {
        return drugService.getDrugDetail(id);
    }

    // 分页查询药品
    @GetMapping
    public Map<String, Object> listDrugs(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String name) {
        Map<String, Object> result = new HashMap<>();
        List<Drug> list = drugService.pageQueryDrug(page, size, name);
        int total = drugService.countDrug(name);
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    // 药品名称唯一性校验
    @GetMapping("/check-name")
    public Map<String, Object> checkDrugName(@RequestParam String name) {
        Map<String, Object> result = new HashMap<>();
        boolean unique = drugService.isDrugNameUnique(name);
        result.put("unique", unique);
        return result;
    }

    // 上传药品图片（多张）
    @PostMapping("/{id}/images")
    public Map<String, Object> uploadDrugImages(@PathVariable Integer id,
                                                @RequestParam("files") MultipartFile[] files,
                                                @RequestParam("imageType") String imageType) {
        Map<String, Object> result = new HashMap<>();
        List<String> filePaths = new ArrayList<>();
        String uploadDir = System.getProperty("user.dir") + "/upload/drug";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        boolean allOk = true;
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String newFileName = System.currentTimeMillis() + "_" + originalFilename;
            File dest = new File(dir, newFileName);
            try {
                file.transferTo(dest);
                com.example.prescription.entity.DrugImage img = new com.example.prescription.entity.DrugImage();
                img.setDrugId(id);
                img.setImagePath("/upload/drug/" + newFileName);
                img.setImageType(imageType);
                img.setSortOrder(0);
                boolean ok = drugImageService.saveDrugImage(img);
                allOk = allOk && ok;
                filePaths.add("/upload/drug/" + newFileName);
            } catch (IOException e) {
                allOk = false;
            }
        }
        result.put("success", allOk);
        result.put("paths", filePaths);
        result.put("msg", allOk ? "上传成功" : "部分或全部上传失败");
        return result;
    }

    // 删除药品图片
    @DeleteMapping("/images/{imageId}")
    public Map<String, Object> deleteDrugImage(@PathVariable Integer imageId) {
        Map<String, Object> result = new HashMap<>();
        boolean ok = drugService.deleteDrugImage(imageId);
        result.put("success", ok);
        result.put("msg", ok ? "删除成功" : "删除失败");
        return result;
    }
}
