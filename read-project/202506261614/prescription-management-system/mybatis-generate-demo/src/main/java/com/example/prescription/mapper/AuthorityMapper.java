package com.example.prescription.mapper;

import com.example.prescription.entity.Authority;
import com.example.prescription.entity.AuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityMapper {
    long countByExample(AuthorityExample example);

    int deleteByExample(AuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Authority row);

    int insertSelective(Authority row);

    List<Authority> selectByExample(AuthorityExample example);

    Authority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Authority row, @Param("example") AuthorityExample example);

    int updateByExample(@Param("row") Authority row, @Param("example") AuthorityExample example);

    int updateByPrimaryKeySelective(Authority row);

    int updateByPrimaryKey(Authority row);
}