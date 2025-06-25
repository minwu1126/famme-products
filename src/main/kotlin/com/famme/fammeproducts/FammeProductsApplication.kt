package com.famme.fammeproducts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class FammeProductsApplication

fun main(args: Array<String>) {
    runApplication<FammeProductsApplication>(*args)
}