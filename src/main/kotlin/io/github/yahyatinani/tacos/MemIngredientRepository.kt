package io.github.yahyatinani.tacos

import io.github.yahyatinani.tacos.core.Ingredient
import org.springframework.stereotype.Repository

@Repository
class MemIngredientRepository : IngredientRepository {
  override fun findAll(): Iterable<Ingredient> = ingredients.values

  override fun findBy(id: String): Ingredient? = ingredients[id]

  override fun save(ingredient: Ingredient): Ingredient {
    TODO("Not yet implemented")
  }

  companion object {
    private val ingredients = mapOf(
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
  }
}
