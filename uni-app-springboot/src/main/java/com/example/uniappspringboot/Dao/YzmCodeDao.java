package com.example.uniappspringboot.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.YzmUserCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YzmCodeDao extends BaseMapper<YzmUserCode> {
}
