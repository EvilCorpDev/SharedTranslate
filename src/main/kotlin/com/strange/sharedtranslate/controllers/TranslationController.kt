package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.services.MongoManager
import com.strange.sharedtranslate.services.parser.TextParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by Zakhar_Kliap on 27-Apr-16.
 */
@Controller
class TranslationController @Autowired constructor(val mongoMan: MongoManager) {

    @RequestMapping("/translate")
    fun translate(): String {
        return "base"
    }

    @RequestMapping("/translate/content")
    fun translateContent(): String {
        return "translate-page"
    }

    @RequestMapping(path = arrayOf("/translate/data"), produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun translateData(): List<String> {
        val classLoader = Thread.currentThread().contextClassLoader
        val textLines = Files.readAllLines(Paths.get( classLoader.getResource("input.txt").toURI()))
        val parsedTextLines = textLines.flatMap { it.split('.') }.map { it + "." }
        return parsedTextLines
    }

    fun saveTranslation() {

    }
}
