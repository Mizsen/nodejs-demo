package com.wuxe.blog.service;

import com.wuxe.blog.entity.FileUpload;
import java.util.List;

public interface FileUploadService {
    // 方法一：保存文件上传记录
    FileUpload saveFile(FileUpload fileUpload);

    // 方法二：根据用户ID获取文件上传列表
    List<FileUpload> getFilesByUserId(Long userId);
}