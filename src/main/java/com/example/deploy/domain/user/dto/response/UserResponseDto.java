package com.example.deploy.domain.user.dto.response;

import com.example.deploy.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

  private Long id;
  private String name;
  private String loginId;
  private String password;
  private String introduce;

  public static UserResponseDto from(User user) {
    return UserResponseDto.builder()
        .id(user.getId())
        .name(user.getName())
        .loginId(user.getLoginId())
        .password(user.getPassword())
        .introduce(user.getIntroduce())
        .build();
  }
}
