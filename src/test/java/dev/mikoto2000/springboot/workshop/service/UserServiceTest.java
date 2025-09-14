package dev.mikoto2000.springboot.workshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mikoto2000.springboot.workshop.bean.User;
import dev.mikoto2000.springboot.workshop.repository.UserMapper;

/**
 * UserServiceTest
 */
@SpringBootTest
public class UserServiceTest {

  @Mock
  // モックする対象
  private UserMapper mapper;

  // テスト対象
  @InjectMocks
  private UserService service;

  @Test
  public void testFindAll() {
    // モックの `findAll` が呼ばれた際の戻り値を指定
    when(mapper.findAll()).thenReturn(List.of(
          new User("mikoto2000"),
          new User("mikoto3000"),
          new User("mikoto4000")
          ));

    List<User> expect = List.of(
          new User("mikoto2000"),
          new User("mikoto3000"),
          new User("mikoto4000")
          );
    List<User> actual = service.findAll();

    assertEquals(expect, actual);
  }

  @BeforeEach
  public void setup() {
  }

  @AfterEach
  public void tearDown() {
  }
}
