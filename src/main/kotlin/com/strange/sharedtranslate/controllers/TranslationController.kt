package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.Article
import com.strange.sharedtranslate.entities.TranslationWrapper
import com.strange.sharedtranslate.repository.TranslationMongoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

/**
 * Created by Zakhar_Kliap on 27-Apr-16.
 */
@Controller
class TranslationController @Autowired constructor(val translationMan: TranslationMongoService) {

    @RequestMapping(path = arrayOf("/translate/{article}", "/translate"))
    fun translate() = "base"

    @RequestMapping("/translate/content")
    fun translateContent() = "translate-page"

    @RequestMapping(path = arrayOf("/translate/data"), produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun translateData(@RequestParam("article") articleTitle: String): Article {
        return Article(articleTitle.replace('_', ' '), translationMan.findAllByArticle(articleTitle))
    }

    @RequestMapping(path = arrayOf("/translate/save"), method = arrayOf(RequestMethod.POST))
    fun saveTranslation(@RequestParam translation: String,
                        @RequestParam("id") originalId: String,
                        @RequestParam(defaultValue = "Zakhar Kliap") author: String): ResponseEntity<String> {
        val toBeUpdate = translationMan.findById(originalId)?.update(listOf(TranslationWrapper(translation, author, Date())))
        if (toBeUpdate != null) {
            translationMan.save(toBeUpdate)
            return ResponseEntity(HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}
