package io.github.yahyatinani.tacos

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloJsonController {
  @RequestMapping("/")
  fun hello() = mapOf("greetings" to "Hello JSON-over-HTTP API!!")
}