package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.User
import com.strange.sharedtranslate.repository.UserMongoService
import com.strange.sharedtranslate.utils.Passwords
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by Zakhar_Kliap on 12-May-16.
 */
@Controller
class RegistrationController @Autowired constructor(val userService: UserMongoService) {

    @RequestMapping("/register")
    fun register() = "base"

    @RequestMapping("/register/content")
    fun registerContent() = "register-page"

    @RequestMapping(path = arrayOf("/register/save"), method = arrayOf(RequestMethod.POST))
    fun saveUser(@RequestParam name: String,
                 @RequestParam login: String,
                 @RequestParam email: String,
                 @RequestParam pass: String): ResponseEntity<String> {
        userService.save(User(null, name, login, email, pass, salt = Passwords.getNextSalt()))
        return ResponseEntity(HttpStatus.OK)
    }
}
