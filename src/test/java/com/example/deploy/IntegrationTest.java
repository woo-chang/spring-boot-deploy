package com.example.deploy;

import com.example.deploy.domain.user.dao.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@Transactional
public class IntegrationTest {

  @Autowired
  protected UserRepository userRepository;

  @Autowired
  protected WebApplicationContext webApplicationContext;

  protected MockMvc mockMvc;

  @BeforeEach
  protected void setUpAll() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .addFilter(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }

  protected String asJsonString(final Object obj) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      final String content = objectMapper.writeValueAsString(obj);
      return content;
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
