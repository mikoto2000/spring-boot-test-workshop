package dev.mikoto2000.springboot.workshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import dev.mikoto2000.springboot.workshop.bean.User;

/**
 * UserMapperTest
 */
@MybatisTest
public class UserMapperTest {

  @Autowired
  private UserMapper mapper;

  @Test
  @Sql("/sql/UserMapperTest-findAll.sql")
  public void testFindAll() {
    List<User> users = mapper.findAll();
    assertEquals(3, users.size());
    assertEquals("mikoto2000", users.get(0).getName());
    assertEquals("mikoto2001", users.get(1).getName());
    assertEquals("mikoto2002", users.get(2).getName());
  }

  @BeforeEach
  public void setup() {
  }

  @AfterEach
  public void tearDown() {
  }
}
