package com.example.prescription.mapper;

import com.example.prescription.entity.User;
import com.example.prescription.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User row);

    int insertSelective(User row);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") User row, @Param("example") UserExample example);

    int updateByExample(@Param("row") User row, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);
}