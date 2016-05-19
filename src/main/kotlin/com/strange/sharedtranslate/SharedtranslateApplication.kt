package com.strange.sharedtranslate

import com.cloudinary.Cloudinary
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@PropertySource("classpath:application.properties")
open class SharedtranslateApplication {

    @Value("\${cloudinary.api_key}") lateinit var api_key: String
    @Value("\${cloudinary.cloud_name}") lateinit var cloud_name: String
    @Value("\${cloudinary.api_secret}") lateinit var api_secret: String


    @Bean
    open fun cloudinary(): Cloudinary {
        val cloudinary = Cloudinary(mapOf("cloud_name" to cloud_name,
                "api_key" to api_key,
                "api_secret" to api_secret))
        return cloudinary
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SharedtranslateApplication::class.java, *args)
}
