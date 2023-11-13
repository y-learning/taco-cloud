package io.github.yahyatinani.tacos.core

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class Taco(
  @field:Size(min = 5, message = "Name must be at least 5 characters!")
  var name: String,
  @field:NotEmpty(message = "You must choose at least 1 ingredient.")
  var ingredients: List<Ingredient> = listOf()
) {
  companion object {
    private val nil = Taco(name = "")

    operator fun invoke() = nil
  }
}
