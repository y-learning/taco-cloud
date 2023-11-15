package io.github.yahyatinani.tacos.ordertaco

import io.github.yahyatinani.tacos.core.Taco
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.CreditCardNumber
import org.hibernate.validator.constraints.Length
import java.io.Serializable
import java.util.Date

data class TacoOrder(
  var id: Long = -1,
  var createdAt: Date = Date(),
  @field:NotBlank(message = "Delivery name is required")
  var deliveryName: String,

  @field:NotBlank(message = "Street is required")
  var deliveryStreet: String,

  @field:NotBlank(message = "City is required")
  var deliveryCity: String,

  @field:NotBlank(message = "State is required")
  @field:Length(min = 2, max = 2)
  var deliveryState: String,

  @field:NotBlank(message = "Zip code is required")
  var deliveryZip: String,

  @field:CreditCardNumber(message = "Not a valid credit card number")
  var ccNumber: String,

  @field:Pattern(
    regexp = "^(0[1-9]|1[0-2])(/)([2-9][0-9])$",
    message = "Must follow format: MM/YY"
  )
  var ccExpiration: String,

  @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  var ccCVV: String,

  var tacos: List<Taco> = listOf()
) : Serializable {
  companion object {
    private const val SERIAL_VERSION_UID = 1L

    private val nil = TacoOrder(
      id = -1,
      createdAt = Date(),
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
