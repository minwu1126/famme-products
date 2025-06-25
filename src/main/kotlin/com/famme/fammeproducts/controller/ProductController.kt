// src/main/kotlin/com/famme/fammeproducts/controller/ProductController.kt
package com.famme.fammeproducts.controller

import com.famme.fammeproducts.model.CreateProductRequest
import com.famme.fammeproducts.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)

    @GetMapping("/")
    fun index(): String {
        logger.info("Accessing index page")
        return "index"
    }

    @GetMapping("/products")
    @ResponseBody
    fun loadProducts(): String {
        logger.info("Loading products endpoint hit")
        val products = productService.getAllProducts()
        logger.info("Found ${products.size} products")

        return if (products.isNotEmpty()) {
            """
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Vendor</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Handle</th>
                    </tr>
                </thead>
                <tbody>
                    ${products.joinToString("") { product -> 
                        val price = if (product.price != null) product.price.toString() else "N/A"
                        """
                            <tr>
                                <td>${product.title}</td>
                                <td>${product.vendor ?: "N/A"}</td>
                                <td>${product.productType ?: "N/A"}</td>
                                <td class="price">""" + "$" + price + """</td>
                                <td>${product.handle}</td>
                            </tr>
                          """.trimIndent()
                    }}
                </tbody>
            </table>
            """.trimIndent()
        } else {
            """<div class="no-results"><p>No products found in database.</p></div>"""
        }
    }

    @PostMapping("/products")
    @ResponseBody
    fun createProduct(@ModelAttribute request: CreateProductRequest): String {
        logger.info("Creating new product: ${request.title}")
        productService.createProduct(request)

        // Return updated products table
        val products = productService.getAllProducts()
        return if (products.isNotEmpty()) {
            """
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Vendor</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Handle</th>
                    </tr>
                </thead>
                <tbody>
                    ${
                        products.joinToString("") { product ->
                            val price = if (product.price != null) product.price.toString() else "N/A"
                            """
                            <tr>
                                <td>${product.title}</td>
                                <td>${product.vendor ?: "N/A"}</td>
                                <td>${product.productType ?: "N/A"}</td>
                                <td class="price">""" + "$" + price + """</td>
                                <td>${product.handle}</td>
                            </tr>
                            """.trimIndent()
                        }
                    }
                </tbody>
            </table>
            """.trimIndent()
        } else {
            """<div class="no-results"><p>No products found in database.</p></div>"""
        }
    }
}