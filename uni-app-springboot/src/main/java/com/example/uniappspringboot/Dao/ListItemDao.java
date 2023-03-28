package com.example.uniappspringboot.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uniappspringboot.Domain.ListItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ListItemDao extends BaseMapper<ListItem> {
}
