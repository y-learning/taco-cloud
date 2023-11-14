package io.github.yahyatinani.tacos.core

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.Date

data class Taco(
  var id: Long = -1,
  var createdAt: Date = Date(),
  @field:Size(min = 5, message = "Name must be at least 5 characters!")
  var name: String,
  @field:NotEmpty(message = "You must choose at least 1 ingredient.")
  var ingredients: List<Ingredient> = listOf()
) {
  companion object {
    private val nil = Taco(id = -1, createdAt = Date(), name = "")

    operator fun invoke() = nil
  }
}
