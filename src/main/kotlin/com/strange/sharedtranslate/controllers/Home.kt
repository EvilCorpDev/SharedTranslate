package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.services.parser.TextParser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by Zakhar_Kliap on 26-Apr-16.
 */
@Controller
class Home {

    @RequestMapping("/")
    fun index(): ModelAndView {
        val modelAndView = ModelAndView("index")
        modelAndView.addObject("textToTranslate", TextParser("input.txt").parseText())
        return modelAndView
    }
}