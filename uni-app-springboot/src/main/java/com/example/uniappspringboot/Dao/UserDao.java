package com.example.uniappspringboot.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
