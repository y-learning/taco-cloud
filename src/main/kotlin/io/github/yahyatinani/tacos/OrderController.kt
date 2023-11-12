package io.github.yahyatinani.tacos

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

@SessionAttributes("tacoOrder")
@RequestMapping("/orders")
@Controller
class OrderController {
  @GetMapping("/current")
  fun orderForm(): String = "order_form"

  @PostMapping
  fun processOrder(order: TacoOrder, sessionStatus: SessionStatus): String {
    log.info("Order submitted: {}", order)
    sessionStatus.setComplete()

    return "redirect:/"
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
  }
}
