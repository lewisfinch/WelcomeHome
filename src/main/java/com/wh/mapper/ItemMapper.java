package com.wh.mapper;

import com.wh.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface ItemMapper {

    @Select("SELECT * FROM item")
    List<Item> list();

}
