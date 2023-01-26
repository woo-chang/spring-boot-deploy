package com.example.deploy.domain.user.application;

import com.example.deploy.domain.user.dao.UserRepository;
import com.example.deploy.domain.user.dto.reqeust.UserRequestDto;
import com.example.deploy.domain.user.dto.response.UserResponseDto;
import com.example.deploy.domain.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    User savedUser = userRepository.save(userRequestDto.toEntity());
    return UserResponseDto.from(savedUser);
  }

  public List<UserResponseDto> getUsers() {
    return userRepository.findAll()
        .stream()
        .map(UserResponseDto::from)
        .collect(Collectors.toList());
  }

  @Transactional
  public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("해당 유저를 찾지 못했습니다."));

    user.setName(userRequestDto.getName());
    user.setLoginId(userRequestDto.getLoginId());
    user.setPassword(userRequestDto.getPassword());
    user.setIntroduce(userRequestDto.getIntroduce());

    return UserResponseDto.from(user);
  }

  @Transactional
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
