package io.github.yahyatinani.tacos.ordertaco

interface OrderRepository {
  fun save(order: TacoOrder): TacoOrder
}
