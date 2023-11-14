package io.github.yahyatinani.tacos.core

import java.util.UUID

data class Ingredient(
  val id: String = UUID.randomUUID().toString(),
  val name: String,
  val type: Type
) {
  enum class Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE }
}
