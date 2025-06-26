package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.demo.config.DataSource;
import com.example.demo.config.DbContextHolder;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;

import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 你的姓名
 * @since 2025-06-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Override
    @DataSource(DbContextHolder.DbType.PRIMARY) // 默认写操作走主库
    public boolean save(UserEntity user) {
        return super.save(user);
    }

    @Override
    @DataSource(DbContextHolder.DbType.SLAVE) // 读操作走从库
    public UserEntity getById(Serializable id) {
        return super.getById(id);
    }
}
