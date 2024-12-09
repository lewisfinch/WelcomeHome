package com.wh.mapper;

import com.wh.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM person WHERE userName = #{userName} and password = #{password}")
    Person getByUsernameAndPassword(Person person);
}
