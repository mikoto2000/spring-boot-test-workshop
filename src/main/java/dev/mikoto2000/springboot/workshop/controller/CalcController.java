package dev.mikoto2000.springboot.workshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.mikoto2000.springboot.workshop.service.CalcService;
import lombok.RequiredArgsConstructor;

/**
 * CalcController
 */
@RestController
@RequiredArgsConstructor
public class CalcController {

  private final CalcService service;

  @GetMapping("/add")
  public int add(
      @RequestParam int a,
      @RequestParam int b) {
    return service.add(a, b);
  }

}
