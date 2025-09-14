package dev.mikoto2000.springboot.workshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * EchoControllerTest
 */
@WebMvcTest(EchoController.class)
public class EchoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testEcho() throws Exception {
    mockMvc.perform(get("/echo").param("message", "12345"))
      .andExpect(status().isOk())
      .andExpect(content().string("12345"));
  }

  @BeforeEach
  public void setup() {
  }

  @AfterEach
  public void tearDown() {
  }
}
