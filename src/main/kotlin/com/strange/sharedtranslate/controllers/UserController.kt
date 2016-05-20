package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.User
import com.strange.sharedtranslate.services.impl.UserMongoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpSession

/**
 * Created by Zakhar_Kliap on 17-May-16.
 */
@Controller
class UserController @Autowired constructor(val userService: UserMongoService) {

    @RequestMapping(path = arrayOf("/user/{login}", "/user"))
    fun user() = "base"

    @RequestMapping("/user/content")
    fun userContent() = "user-page"

    @RequestMapping("/user/data/{login}")
    @ResponseBody
    fun userData(@PathVariable login: String) = userService.findOneByLogin(login)

    @RequestMapping("/user/data")
    @ResponseBody
    fun currentUserData(session: HttpSession): User {
        val user = session.getAttribute("user")
        return user as User
    }
}