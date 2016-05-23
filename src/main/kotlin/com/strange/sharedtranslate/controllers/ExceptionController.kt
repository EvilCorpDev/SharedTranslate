package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.exceptions.EntityNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Controller for handling exceptions
 *
 * Created by Notebook on 22.05.2016.
 */
@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(value = EntityNotFoundException::class)
    fun handleEntityNotFound(exc: EntityNotFoundException): ModelAndView {
        val result = ModelAndView("error")
        result.addObject("errorStack", exc.msg)
        return result
    }

    @ExceptionHandler(value = Throwable::class)
    fun handleOtherExc(exc: Throwable): String {
        return "error"
    }
}