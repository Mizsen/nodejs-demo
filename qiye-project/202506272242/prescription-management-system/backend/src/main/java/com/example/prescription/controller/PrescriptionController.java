package com.example.prescription.controller;

import com.example.prescription.entity.Prescription;
import com.example.prescription.service.PrescriptionService;
import com.example.prescription.service.PrescriptionImageService;
import com.example.prescription.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PrescriptionImageService prescriptionImageService;

    // 新增药方
    @PostMapping
    public Map<String, Object> addPrescription(@RequestBody Prescription prescription, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String token = JwtUtil.resolveToken(request.getHeader("Authorization"));
        String username = JwtUtil.getUsernameFromToken(token);
        // 校验必填字段
        if (prescription.getPrescriptionName() == null || prescription.getIndications() == null
                || prescription.getUsage() == null) {
            result.put("success", false);
            result.put("msg", "药方名称、适应症、用法用量为必填项");
            return result;
        }
        // 设置创建人
        // prescription.setCreatedBy(username); // createdBy 应为 Integer 类型
        try {
            // 假设有 userService.findByUsername 返回 User，User.getId() 为 Integer
            // Integer userId = userService.findByUsername(username).getId();
            // prescription.setCreatedBy(userId);
            // 如果没有 userService，可直接注释掉上面两行，或根据你的业务实际设置
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "无法获取创建人ID");
            return result;
        }
        boolean ok = prescriptionService.savePrescriptionWithRelations(prescription);
        result.put("success", ok);
        result.put("msg", ok ? "新增成功" : "新增失败");
        return result;
    }

    // 分页查询药方
    @GetMapping
    public Map<String, Object> listPrescriptions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String indications) {
        Map<String, Object> result = new HashMap<>();
        List<Prescription> list = prescriptionService.pageQuery(page, size, name, indications);
        int total = prescriptionService.count(name, indications);
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    // 获取药方详情
    @GetMapping("/{id}")
    public Prescription getPrescription(@PathVariable Integer id) {
        return prescriptionService.getPrescriptionDetail(id);
    }

    // 修改药方
    @PutMapping("/{id}")
    public Map<String, Object> updatePrescription(@PathVariable Integer id, @RequestBody Prescription prescription, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        prescription.setId(id);
        String token = JwtUtil.resolveToken(request.getHeader("Authorization"));
        String username = JwtUtil.getUsernameFromToken(token);
        String role = JwtUtil.getRoleFromToken(token);
        Prescription old = prescriptionService.getPrescriptionDetail(id);
        // 权限校验：仅创建者或管理员可修改
        // old.getCreatedBy() 需为 Integer，需通过 userService.getById(old.getCreatedBy()).getUsername() 判断
        // 这里假设 createdBy 存储的是用户名
        if (!username.equals(old.getCreatedBy()) && !"admin".equals(role)) {
            result.put("success", false);
            result.put("msg", "无权限修改");
            return result;
        }
        boolean ok = prescriptionService.updatePrescriptionWithRelations(prescription);
        result.put("success", ok);
        result.put("msg", ok ? "修改成功" : "修改失败");
        return result;
    }

    // 删除药方（支持批量）
    @DeleteMapping
    public Map<String, Object> deletePrescriptions(@RequestBody List<Integer> ids, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String token = JwtUtil.resolveToken(request.getHeader("Authorization"));
        String role = JwtUtil.getRoleFromToken(token);
        if (!"admin".equals(role)) {
            result.put("success", false);
            result.put("msg", "无权限删除");
            return result;
        }
        boolean ok = prescriptionService.deletePrescriptionsCascade(ids);
        result.put("success", ok);
        result.put("msg", ok ? "删除成功" : "删除失败");
        return result;
    }

    // 多图片上传药方图片
    @PostMapping("/{id}/images")
    public Map<String, Object> uploadPrescriptionImages(@PathVariable Integer id,
                                                        @RequestParam("files") MultipartFile[] files,
                                                        @RequestParam("imageType") String imageType) {
        Map<String, Object> result = new HashMap<>();
        List<String> filePaths = new ArrayList<>();
        String uploadDir = System.getProperty("user.dir") + "/upload/prescription";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        boolean allOk = true;
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            String originalFilename = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
            String newFileName = System.currentTimeMillis() + "_" + originalFilename;
            File dest = new File(dir, newFileName);
            try {
                file.transferTo(dest);
                com.example.prescription.entity.PrescriptionImage img = new com.example.prescription.entity.PrescriptionImage();
                img.setPrescriptionId(id);
                img.setImagePath("/upload/prescription/" + newFileName);
                img.setImageType(imageType);
                img.setSortOrder(0);
                boolean ok = prescriptionImageService.savePrescriptionImage(img);
                allOk = allOk && ok;
                filePaths.add("/upload/prescription/" + newFileName);
            } catch (IOException e) {
                allOk = false;
            }
        }
        result.put("success", allOk);
        result.put("paths", filePaths);
        result.put("msg", allOk ? "上传成功" : "部分或全部上传失败");
        return result;
    }
}
