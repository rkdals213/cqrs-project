package com.example.readserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReadServerApplication

fun main(args: Array<String>) {
    runApplication<ReadServerApplication>(*args)
}
