package com.example.deploy.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 45)
  private String name;

  @Column(name = "login_id", nullable = false, unique = true, length = 45)
  private String loginId;

  @Column(name = "password", nullable = false, length = 512)
  private String password;

  @Column(name = "introduce", length = 255)
  private String introduce;

  @Builder
  private User(String name, String loginId, String password, String introduce) {
    this.name = name;
    this.loginId = loginId;
    this.password = password;
    this.introduce = introduce;
  }
}
