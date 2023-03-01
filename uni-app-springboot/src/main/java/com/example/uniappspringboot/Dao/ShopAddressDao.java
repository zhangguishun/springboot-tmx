package com.example.uniappspringboot.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.ShopAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopAddressDao extends BaseMapper<ShopAddress> {
}
