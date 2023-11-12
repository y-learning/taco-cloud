package io.github.yahyatinani.tacos.core

import java.util.UUID

data class Ingredient(
  val id: String = UUID.randomUUID().toString(),
  val name: String,
  val type: Type
) {
  enum class Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE }
}

internal val ingredients = mapOf(
  "FLTO" to Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
  "COTO" to Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
  "GRBF" to Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
  "CARN" to Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
  "TMTO" to Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
  "LETC" to Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
  "CHED" to Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
  "JACK" to Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
  "SLSA" to Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
  "SRCR" to Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
)

fun ingredients(): Collection<Ingredient> = ingredients.values
