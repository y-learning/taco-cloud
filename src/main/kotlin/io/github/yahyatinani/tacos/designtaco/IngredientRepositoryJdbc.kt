package io.github.yahyatinani.tacos.designtaco

import io.github.yahyatinani.tacos.core.Ingredient
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class IngredientRepositoryJdbc(val jdbcTemplate: JdbcTemplate) :
  IngredientRepository {
  override fun findAll(): Iterable<Ingredient> =
    jdbcTemplate.query("SELECT * FROM ingredient") { rs, _ -> toIngredient(rs) }

  override fun findBy(id: String): Ingredient? {
    val sql = "SELECT * FROM ingredient WHERE id=?"
    val function: (ResultSet) -> Ingredient = { rs: ResultSet ->
      rs.next()
      toIngredient(rs)
    }
    return jdbcTemplate.query(sql, function, id)
  }

  override fun save(ingredient: Ingredient): Ingredient {
    jdbcTemplate.update(
      "INSERT INTO ingredient (id, name, type) values (?, ?, ?)",
      ingredient.id,
      ingredient.name,
      ingredient.type.toString()
    )
    return ingredient
  }

  override fun deleteAll() {
    jdbcTemplate.update("delete from ingredient")
  }

  companion object {
    fun toIngredient(rs: ResultSet): Ingredient = Ingredient(
      id = rs.getString("id"),
      name = rs.getString("name"),
      type = Ingredient.Type.valueOf(rs.getString("type"))
    )
  }
}
