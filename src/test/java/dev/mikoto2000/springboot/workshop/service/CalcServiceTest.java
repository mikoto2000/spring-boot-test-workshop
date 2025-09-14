package dev.mikoto2000.springboot.workshop.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * CalcServiceTest
 */
public class CalcServiceTest {

  private CalcService service;

  @Test
  public void testAdd() {
    int a = 1;
    int b = 2;
    int expect = 3;
    int actual = service.add(a, b);
    assertEquals(expect, actual);
  }

  @BeforeEach
  public void setup() {
    service = new CalcService();
  }

  @AfterEach
  public void tearDown() {
  }
}
