package io.github.yahyatinani.tacos

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@AutoConfigureMockMvc
@SpringBootTest
class HomeControllerTest(@Autowired private val mockMvc: MockMvc) {
  @Test
  fun testHomePage() {
    mockMvc.perform(get("/"))
      .andExpect(status().isOk)
      .andExpect(view().name("home"))
      .andExpect(content().string(containsString("Welcome to...")))
  }
}
