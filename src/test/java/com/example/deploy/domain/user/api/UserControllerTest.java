package com.example.deploy.domain.user.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.deploy.IntegrationTest;
import com.example.deploy.domain.user.dto.reqeust.UserRequestDto;
import com.example.deploy.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class UserControllerTest extends IntegrationTest {

  @Nested
  @DisplayName("유저 생성")
  class CreateUser {

    @Test
    @DisplayName("유저 생성에 성공한다.")
    void should_success_when_createUser() throws Exception {
      UserRequestDto request = UserRequestDto.builder()
          .name("hello")
          .loginId("helloLogin")
          .password("helloPassword")
          .introduce("helloIntro")
          .build();

      mockMvc.perform(post("/users")
              .contentType(MediaType.APPLICATION_JSON)
              .content(asJsonString(request)))
          .andDo(print())
          .andExpect(status().isCreated());
    }
  }

  @Nested
  @DisplayName("유저 읽기")
  class GetUsers {

    @Test
    @DisplayName("유저 읽기에 성공한다.")
    void should_success_when_getUsers() throws Exception {
      mockMvc.perform(get("/users"))
          .andDo(print())
          .andExpect(status().isOk());
    }
  }

  @Nested
  @DisplayName("유저 업데이트")
  class UpdateUser {

    @Test
    @DisplayName("유저 업데이트에 성공한다.")
    void should_success_when_updateUser() throws Exception {
      User user = User.builder()
          .name("hello")
          .loginId("helloLogin")
          .password("helloPassword")
          .introduce("helloIntro")
          .build();
      userRepository.save(user);

      UserRequestDto request = UserRequestDto.builder()
          .name("hello2")
          .loginId("helloLogin2")
          .password("helloPassword2")
          .introduce("helloIntro2")
          .build();

      mockMvc.perform(patch("/users/" + user.getId())
              .contentType(MediaType.APPLICATION_JSON)
              .content(asJsonString(request)))
          .andDo(print())
          .andExpect(status().isOk());
    }
  }

  @Nested
  @DisplayName("유저 삭제")
  class DeleteUser {

    @Test
    @DisplayName("유저 삭제에 성공한다.")
    void should_success_when_deleteUser() throws Exception {
      User user = User.builder()
          .name("hello")
          .loginId("helloLogin")
          .password("helloPassword")
          .introduce("helloIntro")
          .build();
      userRepository.save(user);

      mockMvc.perform(delete("/users/" + user.getId()))
          .andDo(print())
          .andExpect(status().isNoContent());
    }
  }
}
