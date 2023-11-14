package io.github.yahyatinani.tacos.core

import org.springframework.ui.ModelMap
import org.springframework.validation.Errors

fun ModelMap.addErrorsAttributes(errors: Errors) = errors.fieldErrors.forEach {
  this["${it.field}Error"] = it.defaultMessage
}
