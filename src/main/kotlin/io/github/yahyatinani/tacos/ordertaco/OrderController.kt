package io.github.yahyatinani.tacos.ordertaco

import io.github.yahyatinani.tacos.core.Taco
import io.github.yahyatinani.tacos.core.addErrorsAttributes
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.ModelAndView

@SessionAttributes("tacoDesigns", "tacoOrder")
@RequestMapping("/orders")
@Controller
class OrderController {
  @ModelAttribute
  fun addTacoOrderModelAttribute(
    model: Model,
    @ModelAttribute("tacoDesigns") tacoDesigns: List<Taco>
  ) {
    val tacoOrder = model.getAttribute("tacoOrder") as TacoOrder? ?: TacoOrder()
    model["tacoOrder"] = tacoOrder.copy(tacos = tacoDesigns)
  }

  @GetMapping("/current")
  fun orderForm(): ModelAndView = ModelAndView("order_form")

  @PostMapping
  fun processOrder(
    model: ModelMap,
    @Valid order: TacoOrder,
    errors: Errors,
    sessionStatus: SessionStatus
  ): ModelAndView {
    if (errors.hasErrors()) {
      model.addErrorsAttributes(errors)
      log.error("order errors: {}", errors)
      return ModelAndView("order_form", model)
    }

    log.info("Order submitted: {}", order)
    sessionStatus.setComplete()

    return ModelAndView("redirect:/", model)
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
  }
}
