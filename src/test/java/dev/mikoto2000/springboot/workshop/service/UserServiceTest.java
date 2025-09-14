package dev.mikoto2000.springboot.workshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.mikoto2000.springboot.workshop.bean.User;
import dev.mikoto2000.springboot.workshop.repository.UserMapper;

/**
 * UserServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  // モックする対象
  private UserMapper mapper;

  // テスト対象
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

  @Test
  // 意味ないテストになっているが、例外創出系テストの練習として実装
  public void testFindWIthException() {
    // モックの `findAll` が呼ばれた際の戻り値を指定
    when(mapper.findAll()).thenThrow(new RuntimeException());

    // findAll 呼び出し時に RuntimeException が発生することを確認
    assertThrows(RuntimeException.class, () -> service.findAll());

  }

  @BeforeEach
  public void setup() {
    service = new UserService(mapper);
  }

  @AfterEach
  public void tearDown() {
    reset(mapper);
  }
}
