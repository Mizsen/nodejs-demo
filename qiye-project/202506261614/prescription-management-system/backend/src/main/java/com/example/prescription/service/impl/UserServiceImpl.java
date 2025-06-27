package com.example.prescription.service.impl;

import com.example.prescription.config.DataSourceContextHolder;
import com.example.prescription.config.RoutingDataSource;
import com.example.prescription.entity.User;
import com.example.prescription.mapper.UserMapper;
import com.example.prescription.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("dynamicDataSource") // 明确指定使用动态数据源
    private DataSource dataSource; // 注入动态数据源

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(int id) {

        log.info("1当前数据源类型: {}", DataSourceContextHolder.getDbType());
        // 验证当前实际数据源
        if (dataSource instanceof RoutingDataSource) {
            DataSourceContextHolder.DbType currentDb = DataSourceContextHolder.getDbType();
            log.info("2当前数据源类型: {}", currentDb);
        }

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }
}
