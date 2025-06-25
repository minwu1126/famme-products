package com.famme.fammeproducts.repository

import com.famme.fammeproducts.model.Product
import com.famme.fammeproducts.model.ProductVariant
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.LocalDateTime

@Repository
class ProductRepository(
    private val jdbcClient: JdbcClient,
    private val objectMapper: ObjectMapper
) {

    fun findAll(): List<Product> {
        return jdbcClient
            .sql("SELECT * FROM products ORDER BY created_at DESC")
            .query { rs, _ ->
                val variantsJson = rs.getString("variants")
                val variants = if (variantsJson != null) {
                    objectMapper.readValue<List<ProductVariant>>(variantsJson)
                } else {
                    emptyList()
                }

                Product(
                    id = rs.getLong("id"),
                    title = rs.getString("title"),
                    handle = rs.getString("handle"),
                    vendor = rs.getString("vendor"),
                    productType = rs.getString("product_type"),
                    price = rs.getBigDecimal("price"),
                    createdAt = rs.getTimestamp("created_at")?.toLocalDateTime(),
                    updatedAt = rs.getTimestamp("updated_at")?.toLocalDateTime(),
                    variants = variants
                )
            }
            .list()
    }

    fun findById(id: Long): Product? {
        return jdbcClient
            .sql("SELECT * FROM products WHERE id = ?")
            .param(id)
            .query { rs, _ ->
                val variantsJson = rs.getString("variants")
                val variants = if (variantsJson != null) {
                    objectMapper.readValue<List<ProductVariant>>(variantsJson)
                } else {
                    emptyList()
                }

                Product(
                    id = rs.getLong("id"),
                    title = rs.getString("title"),
                    handle = rs.getString("handle"),
                    vendor = rs.getString("vendor"),
                    productType = rs.getString("product_type"),
                    price = rs.getBigDecimal("price"),
                    createdAt = rs.getTimestamp("created_at")?.toLocalDateTime(),
                    updatedAt = rs.getTimestamp("updated_at")?.toLocalDateTime(),
                    variants = variants
                )
            }
            .optional()
            .orElse(null)
    }

    fun findByTitleContaining(searchTerm: String): List<Product> {
        return jdbcClient
            .sql("SELECT * FROM products WHERE LOWER(title) LIKE LOWER(?) ORDER BY title")
            .param("%$searchTerm%")
            .query { rs, _ ->
                val variantsJson = rs.getString("variants")
                val variants = if (variantsJson != null) {
                    objectMapper.readValue<List<ProductVariant>>(variantsJson)
                } else {
                    emptyList()
                }

                Product(
                    id = rs.getLong("id"),
                    title = rs.getString("title"),
                    handle = rs.getString("handle"),
                    vendor = rs.getString("vendor"),
                    productType = rs.getString("product_type"),
                    price = rs.getBigDecimal("price"),
                    createdAt = rs.getTimestamp("created_at")?.toLocalDateTime(),
                    updatedAt = rs.getTimestamp("updated_at")?.toLocalDateTime(),
                    variants = variants
                )
            }
            .list()
    }

    fun save(product: Product): Product {
        val variantsJson = if (product.variants?.isNotEmpty() == true) {
            objectMapper.writeValueAsString(product.variants)
        } else null

        if (product.id == null) {
            val id = jdbcClient
                .sql("""
                    INSERT INTO products (title, handle, vendor, product_type, price, variants, updated_at) 
                    VALUES (?, ?, ?, ?, ?, ?::jsonb, CURRENT_TIMESTAMP)
                    RETURNING id
                """)
                .param(product.title)
                .param(product.handle)
                .param(product.vendor)
                .param(product.productType)
                .param(product.price)
                .param(variantsJson)
                .query(Long::class.java)
                .single()

            return product.copy(id = id, createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
        } else {
            jdbcClient
                .sql("""
                    UPDATE products 
                    SET title = ?, handle = ?, vendor = ?, product_type = ?, price = ?, 
                        variants = ?::jsonb, updated_at = CURRENT_TIMESTAMP 
                    WHERE id = ?
                """)
                .param(product.title)
                .param(product.handle)
                .param(product.vendor)
                .param(product.productType)
                .param(product.price)
                .param(variantsJson)
                .param(product.id)
                .update()

            return product.copy(updatedAt = LocalDateTime.now())
        }
    }

    fun deleteById(id: Long): Boolean {
        val rowsAffected = jdbcClient
            .sql("DELETE FROM products WHERE id = ?")
            .param(id)
            .update()
        return rowsAffected > 0
    }

    fun existsByHandle(handle: String): Boolean {
        return jdbcClient
            .sql("SELECT COUNT(*) FROM products WHERE handle = ?")
            .param(handle)
            .query(Int::class.java)
            .single() > 0
    }

    fun count(): Long {
        return jdbcClient
            .sql("SELECT COUNT(*) FROM products")
            .query(Long::class.java)
            .single()
    }
}
