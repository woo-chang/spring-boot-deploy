package com.example.deploy.domain.user.api;

import com.example.deploy.domain.user.application.UserService;
import com.example.deploy.domain.user.dto.reqeust.UserRequestDto;
import com.example.deploy.domain.user.dto.response.UserResponseDto;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
    UserResponseDto user = userService.createUser(userRequestDto);

    return ResponseEntity.created(URI.create("/users" + user.getId())).body(user);
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getUsers() {
    List<UserResponseDto> users = userService.getUsers();

    return ResponseEntity.ok().body(users);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id,
      @RequestBody UserRequestDto userRequestDto) {
    UserResponseDto user = userService.updateUser(id, userRequestDto);

    return ResponseEntity.ok().body(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserResponseDto> deleteUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);

    return ResponseEntity.noContent().build();
  }
}
