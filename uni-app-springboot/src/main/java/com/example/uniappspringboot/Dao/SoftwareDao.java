package com.example.uniappspringboot.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.Software;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SoftwareDao extends BaseMapper<Software> {

}
