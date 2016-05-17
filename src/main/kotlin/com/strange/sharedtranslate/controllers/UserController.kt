package com.strange.sharedtranslate.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by Zakhar_Kliap on 17-May-16.
 */
@Controller
class UserController {

    @RequestMapping(path = arrayOf("/user/{login}", "/user"))
    fun allTexts() = "base"

    @RequestMapping("/user/content")
    fun allTextsContent() = "user-page"

    @RequestMapping("/user/data/{login}")
    @ResponseBody
    fun allTextsData(@PathVariable login: String) {

    }
}