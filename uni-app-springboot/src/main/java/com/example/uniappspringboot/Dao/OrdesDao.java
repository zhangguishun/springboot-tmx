package com.example.uniappspringboot.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdesDao extends BaseMapper<Orders> {
}
