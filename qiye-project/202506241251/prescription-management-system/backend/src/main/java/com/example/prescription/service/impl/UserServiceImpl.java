package com.example.prescription.service.impl;

import com.example.prescription.config.DataSourceContextHolder;
import com.example.prescription.config.RoutingDataSource;
import com.example.prescription.entity.UserEntity;
import com.example.prescription.mapper.UserMapper;
import com.example.prescription.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 你的姓名
 * @since 2025-06-24
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    @Qualifier("dynamicDataSource") // 明确指定使用动态数据源
    private DataSource dataSource; // 注入动态数据源

    @Transactional(readOnly = true)
    @Override
    public UserEntity getById(Serializable id) {

        // 验证当前实际数据源
        if (dataSource instanceof RoutingDataSource) {
            DataSourceContextHolder.DbType currentDb = DataSourceContextHolder.getDbType();
            log.info("当前数据源类型: {}", currentDb);
        }

        System.out.println("id = " + id);
        return super.getById(id);
    }
}
