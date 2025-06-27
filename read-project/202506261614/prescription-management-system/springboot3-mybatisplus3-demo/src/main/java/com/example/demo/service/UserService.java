package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<UserEntity> {



//    @Autowired
//    private UserMapper userMapper;
//
//    public List<UserEntity> getAllUsers() {
//        return userMapper.selectList(null);
//    }
//
//    public void addUser(UserEntity userEntity) {
//        userMapper.insert(userEntity);
//    }
}