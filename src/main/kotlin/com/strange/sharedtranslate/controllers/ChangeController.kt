package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpSession

/**
 * Controller for saving changed user profile information
 *
 * Created by Zakhar_Kliap on 06-Jun-16.
 */
@Controller
class ChangeController {

    @RequestMapping("/change")
    fun change() = "base"

    @RequestMapping("/change/content")
    fun changeContent() = "change-page"

    @RequestMapping("change/data")
    @ResponseBody
    fun changeData(session: HttpSession): User? {
        val user = session.getAttribute("user") ?: return null
        return (user as User).copy(password = "")
    }

    @RequestMapping("change/save")
    fun changeSave(@RequestParam name: String, @RequestParam email: String,
                   @RequestParam("prev-pass") prevPass: String, @RequestParam("new-pass") newPass: String) {

    }
}