package io.github.yahyatinani.tacos.ordertaco

import io.github.yahyatinani.tacos.core.Taco

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
