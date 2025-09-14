package dev.mikoto2000.springboot.workshop.service;

import org.springframework.stereotype.Service;

/**
 * CalcService
 */
@Service
public class CalcService {
  public int add(int a, int b) {
    return a + b;
  }
}
