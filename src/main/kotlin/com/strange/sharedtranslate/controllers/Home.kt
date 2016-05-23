package com.strange.sharedtranslate.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Home controller
 *
 * Created by Zakhar_Kliap on 26-Apr-16.
 */
@Controller
class Home {

    @RequestMapping(path = arrayOf("/", "index"))
    fun index() = "index"
}