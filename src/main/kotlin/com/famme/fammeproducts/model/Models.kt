package com.famme.fammeproducts.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

data class Product(
    val id: Long? = null,
    val title: String,
    val handle: String,
    val vendor: String?,
    @JsonProperty("product_type")
    val productType: String?,
    val price: BigDecimal?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val variants: List<ProductVariant>? = null
)

data class ProductVariant(
    val id: Long?,
    val title: String?,
    val price: String?,
    val sku: String?,
    @JsonProperty("inventory_quantity")
    val inventoryQuantity: Int?
)

data class FammeApiResponse(
    val products: List<FammeProduct>
)

data class FammeProduct(
    val id: Long,
    val title: String,
    val handle: String,
    val vendor: String?,
    @JsonProperty("product_type")
    val productType: String?,
    val variants: List<FammeVariant>
)

data class FammeVariant(
    val id: Long,
    val title: String?,
    val price: String,
    val sku: String?,
    @JsonProperty("inventory_quantity")
    val inventoryQuantity: Int?
)

data class CreateProductRequest(
    val title: String,
    val handle: String,
    val vendor: String?,
    val productType: String?,
    val price: BigDecimal?
)