package com.example.prescription.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.prescription.dto.UserLoginDTO;
import com.example.prescription.dto.UserRegisterDTO;
import com.example.prescription.entity.UserEntity;
import com.example.prescription.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.prescription.utils.JwtUtil;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 你的姓名
 * @since 2025-06-24
 */
@RestController
@RequestMapping("/api/auth")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/register")
  public Map<String, Object> register(@RequestBody UserRegisterDTO dto) {
    UserEntity user = new UserEntity();
    user.setUsername(dto.getUsername());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setRealName(dto.getRealName());
    user.setRole(dto.getRole());
    user.setEnabled(1);
    userService.save(user);

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
    if (
      user == null ||
      !passwordEncoder.matches(dto.getPassword(), user.getPassword())
    ) {
      result.put("msg", "用户名或密码错误");
      return result;
    }
    String token = jwtUtil.generateToken(
      user.getId(),
      user.getUsername(),
      user.getRole()
    );
    result.put("token", token);
    result.put("role", user.getRole());
    result.put("username", user.getUsername());
    return result;
  }
}
