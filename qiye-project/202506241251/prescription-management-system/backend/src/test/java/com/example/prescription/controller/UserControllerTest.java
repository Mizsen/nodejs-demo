// package com.example.prescription.controller;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.example.prescription.dto.UserLoginDTO;
// import com.example.prescription.dto.UserRegisterDTO;
// import com.example.prescription.entity.UserEntity;
// import com.example.prescription.service.UserService;
// import com.example.prescription.utils.JwtUtil;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// @WebMvcTest(UserController.class)
// public class UserControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private UserService userService;

//     @MockBean
//     private BCryptPasswordEncoder passwordEncoder;

//     @MockBean
//     private JwtUtil jwtUtil;

//     private final ObjectMapper objectMapper = new ObjectMapper();


// @Test
// void register_ShouldReturnSuccessMessage() throws Exception {
//     // 准备测试数据
//     UserRegisterDTO registerDTO = new UserRegisterDTO();
//     registerDTO.setUsername("testuser");
//     registerDTO.setPassword("password123");
//     registerDTO.setRealName("Test User");
//     registerDTO.setRole("USER");

//     // 模拟行为
//     when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
//     when(userService.save(any(UserEntity.class))).thenReturn(true);

//     // 执行请求并验证
//     mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(objectMapper.writeValueAsString(registerDTO)))
//             .andExpect(status().isOk())
//             .andExpect(jsonPath("$.msg").value("注册成功"));
// }


// @Test
// void login_WithValidCredentials_ShouldReturnToken() throws Exception {
//     // 准备测试数据
//     UserLoginDTO loginDTO = new UserLoginDTO();
//     loginDTO.setUsername("testuser");
//     loginDTO.setPassword("password123");

//     UserEntity mockUser = new UserEntity();
//     mockUser.setId(1L);
//     mockUser.setUsername("testuser");
//     mockUser.setPassword("encodedPassword");
//     mockUser.setRole("USER");

//     // 模拟行为
//     when(userService.getOne(any())).thenReturn(mockUser);
//     when(passwordEncoder.matches(any(), any())).thenReturn(true);
//     when(jwtUtil.generateToken(any(), any(), any())).thenReturn("mockToken");

//     // 执行请求并验证
//     mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(objectMapper.writeValueAsString(loginDTO)))
//             .andExpect(status().isOk())
//             .andExpect(jsonPath("$.token").exists())
//             .andExpect(jsonPath("$.role").value("USER"))
//             .andExpect(jsonPath("$.username").value("testuser"));
// }

// @Test
// void login_WithInvalidCredentials_ShouldReturnErrorMessage() throws Exception {
//     // 准备测试数据
//     UserLoginDTO loginDTO = new UserLoginDTO();
//     loginDTO.setUsername("wronguser");
//     loginDTO.setPassword("wrongpass");

//     // 模拟行为
//     when(userService.getOne(any())).thenReturn(null);

//     // 执行请求并验证
//     mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(objectMapper.writeValueAsString(loginDTO)))
//             .andExpect(status().isOk())
//             .andExpect(jsonPath("$.msg").value("用户名或密码错误"));
// }

// @Test
// void login_WithWrongPassword_ShouldReturnErrorMessage() throws Exception {
//     // 准备测试数据
//     UserLoginDTO loginDTO = new UserLoginDTO();
//     loginDTO.setUsername("testuser");
//     loginDTO.setPassword("wrongpass");

//     UserEntity mockUser = new UserEntity();
//     mockUser.setUsername("testuser");
//     mockUser.setPassword("encodedPassword");

//     // 模拟行为
//     when(userService.getOne(any())).thenReturn(mockUser);
//     when(passwordEncoder.matches(any(), any())).thenReturn(false);

//     // 执行请求并验证
//     mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(objectMapper.writeValueAsString(loginDTO)))
//             .andExpect(status().isOk())
//             .andExpect(jsonPath("$.msg").value("用户名或密码错误"));
// }

// }