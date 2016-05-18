package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.services.impl.UserMongoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpSession

/**
 * Created by Zakhar_Kliap on 18-May-16.
 */
@Controller
class AuthController @Autowired constructor(val userService: UserMongoService){

    @RequestMapping("/auth")
    fun auth() = "base"

    @RequestMapping("/auth/content")
    fun authContent() = "auth-page"

    @RequestMapping(path = arrayOf("/auth"), method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun authLogin(@RequestParam login: String, @RequestParam pass: String, session: HttpSession): Map<String, Boolean> {
        val user = userService.checkUser(login, pass)
        val granted = user != null
        if (granted) {
            session.setAttribute("user", user)
            println(user)
        }
        return mapOf("granted" to granted)
    }
}