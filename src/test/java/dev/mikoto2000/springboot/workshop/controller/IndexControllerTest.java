package dev.mikoto2000.springboot.workshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * IndexControllerTest
 */
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testEcho() throws Exception {
    String html = mockMvc.perform(get("/index"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    Document doc = Jsoup.parse(html);
    assertEquals(doc.selectFirst("#message").text(), "Hello, World!!!");
  }

  @Test
  public void testEchoWithName() throws Exception {
    String html = mockMvc.perform(get("/index").param("name", "12345"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    Document doc = Jsoup.parse(html);
    assertEquals(doc.selectFirst("#message").text(), "Hello, 12345!!!");
  }

  @BeforeEach
  public void setup() {
  }

  @AfterEach
  public void tearDown() {
  }
}
