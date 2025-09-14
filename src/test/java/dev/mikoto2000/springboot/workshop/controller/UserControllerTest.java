package dev.mikoto2000.springboot.workshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.mikoto2000.springboot.workshop.bean.User;
import dev.mikoto2000.springboot.workshop.service.UserService;

/**
 * UserControllerTest
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService service;

  @Test
  public void testUsers() throws Exception {
    when(service.findAll()).thenReturn(List.of(new User("mikoto2000")));

    mockMvc.perform(get("/users"))
      .andExpect(status().isOk())
      .andExpect(content().string("[{\"name\":\"mikoto2000\"}]"));
  }

  @BeforeEach
  public void setup() {
  }

  @AfterEach
  public void tearDown() {
  }
}
