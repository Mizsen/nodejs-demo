package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Slf4j
@Service
public class DatabaseFileSyncService {

    // 主库文件路径
    private final Path primaryDbPath = Paths.get("data/prescription_write.db");
    // 从库文件路径
    private final Path slaveDbPath = Paths.get("data/prescription_read.db");

    /**
     * 每5分钟同步一次
     */
    @Scheduled(fixedRate = 1 * 60 * 1000)
    public void syncDatabaseFiles() {

        log.info("定时同步任务开始执行...");

        try {
            // 1. 获取文件锁（避免在复制过程中被写入）
            RandomAccessFile lockFile = new RandomAccessFile(primaryDbPath.toFile(), "rw");
            FileLock lock = lockFile.getChannel().tryLock();

            if (lock != null) {
                try {
                    // 2. 执行文件复制
                    Files.copy(primaryDbPath, slaveDbPath,
                            StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.COPY_ATTRIBUTES);

                    log.info("数据库文件同步成功: " + new Date());
                } finally {
                    lock.release();
                    lockFile.close();
                }
            } else {
                log.info("获取文件锁失败，跳过本次同步");
            }
        } catch (IOException e) {
//            e.printStackTrace();
            log.info("数据库文件同步失败: " + e.getMessage());
            log.error("同步失败", e);
        }
    }
}