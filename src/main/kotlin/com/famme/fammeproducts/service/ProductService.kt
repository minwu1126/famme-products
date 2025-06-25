package com.famme.fammeproducts.service

import com.famme.fammeproducts.model.*
import com.famme.fammeproducts.repository.ProductRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(ProductService::class.java)

    fun getAllProducts(): List<Product> = productRepository.findAll()

    fun getProductById(id: Long): Product? = productRepository.findById(id)

    fun searchProducts(searchTerm: String): List<Product> =
        productRepository.findByTitleContaining(searchTerm)

    fun createProduct(request: CreateProductRequest): Product {
        val product = Product(
            title = request.title,
            handle = request.handle,
            vendor = request.vendor,
            productType = request.productType,
            price = request.price
        )
        return productRepository.save(product)
    }

    fun updateProduct(id: Long, request: CreateProductRequest): Product? {
        val existingProduct = productRepository.findById(id) ?: return null

        val updatedProduct = existingProduct.copy(
            title = request.title,
            handle = request.handle,
            vendor = request.vendor,
            productType = request.productType,
            price = request.price
        )

        return productRepository.save(updatedProduct)
    }

    fun deleteProduct(id: Long): Boolean = productRepository.deleteById(id)

    @Scheduled(initialDelay = 0, fixedRate = 3600000) // Run immediately, then every hour
    fun fetchAndSaveProducts() {
        logger.info("Starting scheduled job to fetch products from famme.no")

        try {
            val response = restTemplate.getForObject(
                "https://famme.no/products.json",
                FammeApiResponse::class.java
            ) ?: run {
                logger.error("Failed to fetch products - null response")
                return
            }

            val existingCount = productRepository.count()
            if (existingCount >= 50) {
                logger.info("Already have $existingCount products, skipping fetch")
                return
            }

            val productsToSave = response.products
                .take(50 - existingCount.toInt())
                .filter { !productRepository.existsByHandle(it.handle) }

            productsToSave.forEach { fammeProduct ->
                try {
                    val variants = fammeProduct.variants.map { variant ->
                        ProductVariant(
                            id = variant.id,
                            title = variant.title,
                            price = variant.price,
                            sku = variant.sku,
                            inventoryQuantity = variant.inventoryQuantity
                        )
                    }

                    val price = fammeProduct.variants.firstOrNull()?.price?.let {
                        try { BigDecimal(it) } catch (e: Exception) { null }
                    }

                    val product = Product(
                        title = fammeProduct.title,
                        handle = fammeProduct.handle,
                        vendor = fammeProduct.vendor,
                        productType = fammeProduct.productType,
                        price = price,
                        variants = variants
                    )

                    productRepository.save(product)
                    logger.debug("Saved product: ${product.title}")
                } catch (e: Exception) {
                    logger.error("Error saving product ${fammeProduct.title}: ${e.message}")
                }
            }

            logger.info("Successfully processed ${productsToSave.size} products")
        } catch (e: Exception) {
            logger.error("Error fetching products from famme.no: ${e.message}", e)
        }
    }
}
