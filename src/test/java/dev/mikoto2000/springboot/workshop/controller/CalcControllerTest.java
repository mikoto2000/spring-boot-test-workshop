package dev.mikoto2000.springboot.workshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.mikoto2000.springboot.workshop.service.CalcService;

/**
 * CalcControllerTest
 */
@WebMvcTest(CalcController.class)
public class CalcControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CalcService service;

  @Test
  public void testAdd() throws Exception {
    when(service.add(anyInt(), anyInt())).thenReturn(3);

    mockMvc.perform(get("/add")
        .param("a", "1")
        .param("b", "2"))
      .andExpect(status().isOk())
      .andExpect(content().string("3"));

    //// service の add メソッドに、想定通りの引数が渡されているかを確認

    // 引数確認用 Captor
    ArgumentCaptor<Integer> aCaptor = ArgumentCaptor.forClass(Integer.class);
    ArgumentCaptor<Integer> bCaptor = ArgumentCaptor.forClass(Integer.class);
    verify(service, times(1)).add(aCaptor.capture(), bCaptor.capture());
    assertEquals(1, aCaptor.getValue());
    assertEquals(2, bCaptor.getValue());

  }

  @BeforeEach
  public void setup() {
  }

  @AfterEach
  public void tearDown() {
  }
}
