package com.strange.sharedtranslate.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Controller that combine translations into single one for selected article
 *
 * Created by Zakhar_Kliap on 23-May-16.
 */
@Controller
class ReadingController {

    @RequestMapping("/read/{articleTitle}")
    fun read() = "base"

    @RequestMapping("/read/{articleTitle}/content")
    fun readContent() = "read-page"

    @RequestMapping("/read/{articleTitle}/data")
    fun readData() {

    }
}