package com.example.deploy.domain.user.dto.reqeust;

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
public class UserRequestDto {

  private String name;
  private String loginId;
  private String password;
  private String introduce;

  public User toEntity() {
    return User.builder()
        .name(name)
        .loginId(loginId)
        .password(password)
        .introduce(introduce)
        .build();
  }
}
