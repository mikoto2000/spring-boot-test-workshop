package dev.mikoto2000.springboot.workshop.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import dev.mikoto2000.springboot.workshop.repository.UserMapper;
import dev.mikoto2000.springboot.workshop.util.UserUtil;
import lombok.RequiredArgsConstructor;

/**
 * UserService
 */
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserMapper userMapper;

  /**
   * 指定した usreId のユーザーの年齢を返却する。
   *
   * @param userId ユーザー ID
   * @return ユーザーの年齢
   */
  public Integer getAge(Long userId) {

    // ユーザーの取得
    var userOpt = userMapper.findById(userId);
    if (userOpt.isEmpty()) {
      throw new RuntimeException(String.format("対象ユーザーが見つかりませんでした(userId: %d)", userId));
    }
    var user = userOpt.get();

    // 誕生日の取得
    // 本当は User クラスにあるべきだけど
    // 説明の簡単化のため UserUtil クラスに実装している
    var today = LocalDate.now();
    var birthday = UserUtil.getAge(user.getBirthday(), today);

    return birthday;
  }
}

