package io.github.yahyatinani.tacos.designtaco

import io.github.yahyatinani.tacos.core.Ingredient
import io.github.yahyatinani.tacos.core.Taco
import io.github.yahyatinani.tacos.core.ingredients
import io.github.yahyatinani.tacos.ordertaco.TacoOrder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes

@SessionAttributes("tacoOrder")
@RequestMapping("/design")
@Controller
class DesignTacoController {
  @GetMapping
  fun designForm(model: Model): String {
    model.addAllAttributes(
      ingredients()
        .groupBy { it.type }
        .mapKeys { "${it.key}".lowercase() }
        .plus("taco" to Taco())
        .plus("tacoOrder" to (model.getAttribute("tacoOrder") ?: TacoOrder()))
    )

    return "design"
  }

  @PostMapping
  fun processTaco(
    model: Model,
    taco: Taco,
    @ModelAttribute("tacoOrder") tacoOrder: TacoOrder
  ): String {
    model["tacoOrder"] = tacoOrder.copy(tacos = tacoOrder.tacos + taco)
    log.info("Processing taco: {}", taco)

    return "redirect:/orders/current"
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
  }
}

@Component
object IngredientByIdConverter : Converter<String, Ingredient> {
  override fun convert(id: String): Ingredient? = ingredients[id]
}
