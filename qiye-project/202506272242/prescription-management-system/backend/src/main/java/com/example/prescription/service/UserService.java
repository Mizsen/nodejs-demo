package com.example.prescription.service;

import com.example.prescription.entity.User;

public interface UserService {

    public User findUserById(int id);

    public int save(User user);

    public User findByUsername(String username);

    public int update(User user);

}
