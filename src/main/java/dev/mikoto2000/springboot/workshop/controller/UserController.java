package dev.mikoto2000.springboot.workshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mikoto2000.springboot.workshop.bean.User;
import dev.mikoto2000.springboot.workshop.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 * UserController
 */
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping("/users")
  public List<User> users() {
    return service.findAll();
  }
}
