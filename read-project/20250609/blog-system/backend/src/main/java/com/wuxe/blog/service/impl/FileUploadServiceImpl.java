package com.wuxe.blog.service.impl;

import com.wuxe.blog.entity.FileUpload;
import com.wuxe.blog.repository.FileUploadRepository;
import com.wuxe.blog.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Override
    public FileUpload saveFile(FileUpload fileUpload) {
        fileUpload.setCreatedAt(LocalDateTime.now());
        return fileUploadRepository.save(fileUpload);
    }

    @Override
    public List<FileUpload> getFilesByUserId(Long userId) {
        return fileUploadRepository.findByUserId(userId);
    }
} 