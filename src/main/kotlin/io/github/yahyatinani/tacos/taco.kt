package io.github.yahyatinani.tacos

import io.github.yahyatinani.tacos.Ingredient.Type
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.UUID

data class Ingredient(
  val id: String = UUID.randomUUID().toString(),
  val name: String,
  val type: Type
) {
  enum class Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE }
}

data class Taco(
  val name: String,
  val ingredients: List<Ingredient> = listOf()
) {
  companion object {
    private val nil = Taco(name = "")

    operator fun invoke() = nil
  }
}

data class TacoOrder(
  var deliveryName: String,
  var deliveryStreet: String,
  var deliveryCity: String,
  var deliveryState: String,
  var deliveryZip: String,
  var ccNumber: String,
  var ccExpiration: String,
  var ccCVV: String,
  var tacos: List<Taco> = listOf()
) {
  companion object {
    private val nil = TacoOrder(
      deliveryName = "",
      deliveryStreet = "",
      deliveryCity = "",
      deliveryState = "",
      deliveryZip = "",
      ccNumber = "",
      ccExpiration = "",
      ccCVV = ""
    )

    operator fun invoke() = nil
  }
}

internal val ingredients = mapOf(
  "FLTO" to Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
  "COTO" to Ingredient("COTO", "Corn Tortilla", Type.WRAP),
  "GRBF" to Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
  "CARN" to Ingredient("CARN", "Carnitas", Type.PROTEIN),
  "TMTO" to Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
  "LETC" to Ingredient("LETC", "Lettuce", Type.VEGGIES),
  "CHED" to Ingredient("CHED", "Cheddar", Type.CHEESE),
  "JACK" to Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
  "SLSA" to Ingredient("SLSA", "Salsa", Type.SAUCE),
  "SRCR" to Ingredient("SRCR", "Sour Cream", Type.SAUCE)
)

fun ingredients() = ingredients.values

@Component
class IngredientByIdConverter : Converter<String, Ingredient> {
  override fun convert(id: String): Ingredient? = ingredients[id]
}
