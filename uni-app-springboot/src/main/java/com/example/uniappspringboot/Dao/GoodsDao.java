package com.example.uniappspringboot.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsDao extends BaseMapper<Goods> {
}
