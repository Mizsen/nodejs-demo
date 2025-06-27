package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
//
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserRegisterDTO dto) {
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword("1111");
        user.setRealName(dto.getRealName());
        user.setRole("ADMIN");
        user.setEnabled(1);
        userService.save(user);
        System.out.println(JwtUtil.generateToken(dto.getUsername()));
        System.out.println(passwordEncoder.encode(dto.getUsername()));
        System.out.println(passwordEncoder.matches(dto.getUsername(), passwordEncoder.encode(dto.getUsername())));

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "注册成功");
        return result;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserLoginDTO dto) {
        UserEntity user = userService.getOne(
                new QueryWrapper<UserEntity>().eq("username", dto.getUsername())
        );
        Map<String, Object> result = new HashMap<>();
//        if (
//                user == null ||
//                        !passwordEncoder.matches(dto.getPassword(), user.getPassword())
//        ) {
//            result.put("msg", "用户名或密码错误");
//            return result;
//        }
//        String token = jwtUtil.generateToken(
//                user.getId(),
//                user.getUsername(),
//                user.getRole()
//        );
        result.put("token", "token");
        result.put("role", user.getRole());
        result.put("username", user.getUsername());
        return result;
    }
}