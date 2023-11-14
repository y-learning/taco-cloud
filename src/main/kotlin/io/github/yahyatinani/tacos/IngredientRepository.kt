package io.github.yahyatinani.tacos

import io.github.yahyatinani.tacos.core.Ingredient

interface IngredientRepository {
  fun findAll(): Iterable<Ingredient>
  fun findBy(id: String): Ingredient?
  fun save(ingredient: Ingredient): Ingredient
}
