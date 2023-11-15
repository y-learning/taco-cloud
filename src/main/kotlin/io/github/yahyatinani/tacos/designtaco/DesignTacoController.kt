package io.github.yahyatinani.tacos.designtaco

import io.github.yahyatinani.tacos.core.Ingredient
import io.github.yahyatinani.tacos.core.Taco
import io.github.yahyatinani.tacos.core.addErrorsAttributes
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.servlet.ModelAndView

@SessionAttributes("tacoDesigns")
@RequestMapping("/design")
@Controller
class DesignTacoController(
  private val ingredientRepository: IngredientRepository
) {
  @ModelAttribute
  fun addIngredientsModelAttributes(model: ModelMap) {
    model.addAllAttributes(
      ingredientRepository
        .findAll()
        .groupBy { it.type }
        .mapKeys { "${it.key}".lowercase() }
    )
  }

  @ModelAttribute
  fun addTacoDesignsModelAttribute(model: ModelMap) {
    if (model["tacoDesigns"] == null) {
      model.put("tacoDesigns", emptyList<Taco>())
    }
  }

  @GetMapping
  fun designForm(model: ModelMap) = ModelAndView("design", model)

  @PostMapping
  fun processTaco(
    model: ModelMap,
    @Valid taco: Taco,
    errors: Errors,
    @ModelAttribute("tacoDesigns") tacoDesigns: List<Taco>
  ): ModelAndView {
    if (errors.hasErrors()) {
      model.addErrorsAttributes(errors)
      log.error("taco errors: {}", errors)
      return ModelAndView("design")
    }

    model["tacoDesigns"] = tacoDesigns + taco
    log.info("Processing taco: {}", taco)

    return ModelAndView("redirect:/orders/current", model)
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
  }
}

@Component
class IngredientByIdConverter(val repository: IngredientRepository) :
  Converter<String, Ingredient> {
  override fun convert(id: String): Ingredient? = repository.findBy(id)
}
