package com.example.uniappspringboot.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.PayOrders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayOrdersDao extends BaseMapper<PayOrders> {
}
