package com.strange.sharedtranslate

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SharedtranslateApplication

fun main(args: Array<String>) {
    SpringApplication.run(SharedtranslateApplication::class.java, *args)
}
