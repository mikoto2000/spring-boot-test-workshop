package dev.mikoto2000.springboot.workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Nullable;

/**
 * IndexController
 */
@Controller
public class IndexController {

  @GetMapping("/index")
  public String index(
      @RequestParam @Nullable String name,
      Model model) {

    String returnName = name;
    if (name == null) {
      returnName = "World";
    }

    model.addAttribute("name", returnName);

    return "index";
  }
}
