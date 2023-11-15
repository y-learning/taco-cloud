package io.github.yahyatinani.tacos.ordertaco

import io.github.yahyatinani.tacos.core.Ingredient
import io.github.yahyatinani.tacos.core.Taco
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Statement.RETURN_GENERATED_KEYS
import java.util.Date

@Repository
class OrderRepositoryJdbc(val jdbcTemplate: JdbcTemplate) : OrderRepository {
  private fun saveTacoIngredients(tacoId: Long, ingredients: List<Ingredient>) {
    val sql = """
      INSERT INTO taco_ingredient (taco_id, ingredient_id, order_key) 
      VALUES (?, ?, ?)
    """.trimIndent()
    ingredients.forEachIndexed { order, ingredient ->
      jdbcTemplate.update(
        sql,
        tacoId,
        ingredient.id,
        order
      )
    }
  }

  private fun saveTacoOrder(orderId: Long, taco: Taco): Long {
    val tacoSql = "INSERT INTO taco (name, created_at) VALUES (?, ?)"
    val keyHolder = GeneratedKeyHolder()
    taco.createdAt = Date()
    jdbcTemplate.update(
      { connection ->
        connection.prepareStatement(tacoSql, RETURN_GENERATED_KEYS).apply {
          setObject(1, taco.name)
          setObject(2, taco.createdAt)
        }
      },
      keyHolder
    )
    val tacoId = keyHolder.key!!.toLong()
    taco.id = tacoId

    jdbcTemplate.update(
      { connection ->
        val orderTacoSql = """
          INSERT INTO order_taco (order_id, taco_id)
          VALUES (?, ?)
        """.trimIndent()
        connection.prepareStatement(orderTacoSql, RETURN_GENERATED_KEYS).apply {
          setObject(1, orderId)
          setObject(2, tacoId)
        }
      },
      keyHolder
    )

    saveTacoIngredients(tacoId, taco.ingredients)

    return tacoId
  }

  @Transactional
  override fun save(order: TacoOrder): TacoOrder {
    val sql = """
      insert into "order" 
      (delivery_name, delivery_street, delivery_city, delivery_state, 
       delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at)
      values (?,?,?,?,?,?,?,?,?)
    """.trimIndent()

    val keyHolder = GeneratedKeyHolder()
    order.createdAt = Date()
    jdbcTemplate.update(
      { connection ->
        connection.prepareStatement(sql, RETURN_GENERATED_KEYS).apply {
          setObject(1, order.deliveryName)
          setObject(2, order.deliveryStreet)
          setObject(3, order.deliveryCity)
          setObject(4, order.deliveryState)
          setObject(5, order.deliveryZip)
          setObject(6, order.ccNumber)
          setObject(7, order.ccExpiration)
          setObject(8, order.ccCVV)
          setObject(9, order.createdAt)
        }
      },
      keyHolder
    )
    val orderId = keyHolder.key!!.toLong()
    order.tacos.forEach { taco ->
      saveTacoOrder(orderId, taco)
    }
    return order.apply { id = orderId }
  }
}
