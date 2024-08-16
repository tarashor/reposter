package com.tarashor.reposter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReposterApplication

fun main(args: Array<String>) {
    runApplication<ReposterApplication>(*args)
}
