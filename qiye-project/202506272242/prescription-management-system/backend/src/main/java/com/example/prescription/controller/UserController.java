package com.example.prescription.controller;

import com.example.prescription.dto.UserLoginDTO;
import com.example.prescription.dto.UserRegisterDTO;
import com.example.prescription.entity.User;
import com.example.prescription.service.UserService;
import com.example.prescription.utils.JsonUtils;
import com.example.prescription.utils.JwtUtil;
import com.example.prescription.utils.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // 账号登录失败计数器（可用Redis或DB优化）
    private final Map<String, Integer> loginFailCount = new HashMap<>();
    private final int MAX_FAIL = 5;

    private boolean isPasswordStrong(String password) {
        if (password == null) return false;
        return password.length() >= 8
                && Pattern.compile("[A-Z]").matcher(password).find()
                && Pattern.compile("[a-z]").matcher(password).find()
                && Pattern.compile("[0-9]").matcher(password).find()
                && Pattern.compile("[^A-Za-z0-9]").matcher(password).find();
    }

    private List<String> getMenuByRole(String role) {
        if ("admin".equals(role)) {
            return Arrays.asList("用户管理", "药方管理", "药品管理", "系统设置");
        } else if ("doctor".equals(role)) {
            return Arrays.asList("药方管理", "药品查询");
        } else if ("pharmacist".equals(role)) {
            return Arrays.asList("药品管理", "药方审核");
        }
        return Collections.emptyList();
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserRegisterDTO dto, HttpServletRequest request) {
        log.info(RequestLogUtil.printRequestParams(request, dto));
        Map<String, Object> result = new HashMap<>();
        if (!isPasswordStrong(dto.getPassword())) {
            result.put("success", false);
            result.put("msg", "密码强度不足，需包含大小写字母、数字、特殊字符，长度8位以上");
            return result;
        }
        User exist = userService.findByUsername(dto.getUsername());
        if (exist != null) {
            result.put("success", false);
            result.put("msg", "用户名已存在");
            return result;
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRealName(dto.getRealName());
        user.setRole(dto.getRole());
        user.setEnabled(1);
        int count = userService.save(user);
        if (count > 0) {
            result.put("success", true);
            result.put("msg", "注册成功");
        } else {
            result.put("success", false);
            result.put("msg", "注册失败");
        }
        return result;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserLoginDTO dto, HttpServletRequest request) {
        log.info(RequestLogUtil.printRequestParams(request, dto));
        Map<String, Object> result = new HashMap<>();
        User user = userService.findByUsername(dto.getUsername());
        if (user == null) {
            result.put("success", false);
            result.put("msg", "用户名或密码错误");
            return result;
        }
        if (user.getEnabled() == 0) {
            result.put("success", false);
            result.put("msg", "账号已被锁定，请联系管理员");
            return result;
        }
        if (loginFailCount.getOrDefault(user.getUsername(), 0) >= MAX_FAIL) {
            user.setEnabled(0);
            userService.update(user);
            result.put("success", false);
            result.put("msg", "账号已被锁定，请联系管理员");
            return result;
        }
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            loginFailCount.put(user.getUsername(), loginFailCount.getOrDefault(user.getUsername(), 0) + 1);
            result.put("success", false);
            result.put("msg", "用户名或密码错误");
            return result;
        }
        loginFailCount.remove(user.getUsername());
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        result.put("success", true);
        result.put("token", token);
        result.put("role", user.getRole());
        result.put("username", user.getUsername());
        result.put("menu", getMenuByRole(user.getRole()));
        result.put("expire", jwtUtil.getExpireTime(token));
        return result;
    }

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(HttpServletRequest request) {
        log.info(RequestLogUtil.printRequestParams(request, null));
        String token = JwtUtil.resolveToken(request.getHeader("Authorization"));
        String username = jwtUtil.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        Map<String, Object> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("role", user.getRole());
        result.put("menu", getMenuByRole(user.getRole()));
        return result;
    }

    @PostMapping("/refresh-token")
    public Map<String, Object> refreshToken(HttpServletRequest request) {
        log.info(RequestLogUtil.printRequestParams(request, null));
        String oldToken = JwtUtil.resolveToken(request.getHeader("Authorization"));
        if (!jwtUtil.validateToken(oldToken)) {
            throw new RuntimeException("Token已过期");
        }
        String username = jwtUtil.getUsernameFromToken(oldToken);
        String role = jwtUtil.getRoleFromToken(oldToken);
        String newToken = jwtUtil.generateToken(username, role);
        Map<String, Object> result = new HashMap<>();
        result.put("token", newToken);
        result.put("expire", jwtUtil.getExpireTime(newToken));
        return result;
    }
}
