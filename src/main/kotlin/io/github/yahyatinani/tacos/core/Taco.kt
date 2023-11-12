package io.github.yahyatinani.tacos.core

data class Taco(
  val name: String,
  val ingredients: List<Ingredient> = listOf()
) {
  companion object {
    private val nil = Taco(name = "")

    operator fun invoke() = nil
  }
}
