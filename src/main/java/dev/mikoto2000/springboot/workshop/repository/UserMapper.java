package dev.mikoto2000.springboot.workshop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dev.mikoto2000.springboot.workshop.bean.User;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper {

  @Select("select * from \"user\"")
  List<User> findAll();
}
