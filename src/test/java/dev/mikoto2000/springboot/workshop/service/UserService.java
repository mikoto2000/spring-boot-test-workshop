package dev.mikoto2000.springboot.workshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.mikoto2000.springboot.workshop.bean.User;
import dev.mikoto2000.springboot.workshop.repository.UserMapper;
import lombok.RequiredArgsConstructor;

/**
 * UserService
 */
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserMapper userMapper;

  public List<User> findAll() {
    return userMapper.findAll();
  }

}
