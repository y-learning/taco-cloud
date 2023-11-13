package io.github.yahyatinani.tacos.ordertaco

import io.github.yahyatinani.tacos.core.Taco
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

@SessionAttributes("tacoDesigns")
@RequestMapping("/orders")
@Controller
class OrderController {
  @ModelAttribute
  fun addTacoOrderModelAttribute(
    model: Model,
    @ModelAttribute("tacoDesigns") tacoDesigns: List<Taco>
  ) {
    val tacoOrder =
      (model.getAttribute("tacoOrder") as TacoOrder?) ?: TacoOrder()

    model["tacoOrder"] = tacoOrder.copy(tacos = tacoDesigns)
  }

  @GetMapping("/current")
  fun orderForm(): String = "order_form"

  @PostMapping
  fun processOrder(
    @Valid order: TacoOrder,
    errors: Errors,
    sessionStatus: SessionStatus
  ): String {
    if (errors.hasErrors()) {
      return "order_form"
    }

    log.info("Order submitted: {}", order)
    sessionStatus.setComplete()

    return "redirect:/"
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
  }
}
