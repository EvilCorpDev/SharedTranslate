package com.strange.sharedtranslate.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

/**
 * Controller for logout action
 *
 * Created by Zakhar_Kliap on 23-May-16.
 */
@Controller
class LogOutController {

    @RequestMapping("/logout")
    fun logOut(session: HttpSession): String {
        session.invalidate()
        return "redirect:/index"
    }
}