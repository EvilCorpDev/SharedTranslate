package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.services.parser.TextParser
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

/**
 * Created by Zakhar_Kliap on 27-Apr-16.
 */
@Controller
class TranslationController() {

    @RequestMapping("/translate")
    fun translate(): ModelAndView {
        return ModelAndView("base")
    }

    @RequestMapping(path = arrayOf("/translate/data"), produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun translateData(): String {
        return TextParser("input.txt").parseText()
    }
}
