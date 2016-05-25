package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.ReadingPages
import com.strange.sharedtranslate.services.ReadingServiceActions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controller that combine translations into single one for selected article
 *
 * Created by Zakhar_Kliap on 23-May-16.
 */
@Controller
class ReadingController @Autowired constructor(val readingService: ReadingServiceActions){

    @RequestMapping("/read/{articleTitle}")
    fun read() = "base"

    @RequestMapping("/read/content")
    fun readContent() = "read-page"

    @RequestMapping("/read/{articleTitle}/data")
    @ResponseBody
    fun readData(@PathVariable articleTitle: String): ReadingPages {
        println("Some text")
        val leftPage = readingService.buildArticleTranslateWithPage(articleTitle, 0)
        val rightPage = readingService.buildArticleTranslateWithPage(articleTitle, leftPage.last().number)
        return ReadingPages(leftPage, rightPage)
    }
}