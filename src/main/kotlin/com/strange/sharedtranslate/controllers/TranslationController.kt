package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.Article
import com.strange.sharedtranslate.entities.TextTranslationWrapper
import com.strange.sharedtranslate.entities.TranslationWrapper
import com.strange.sharedtranslate.repository.MongoManager
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
class TranslationController @Autowired constructor(val mongoMan: MongoManager) {

    @RequestMapping(path = arrayOf("/translate/{article}", "/translate"))
    fun translate(): String {
        return "base"
    }

    @RequestMapping("/translate/content")
    fun translateContent(): String {
        return "translate-page"
    }

    @RequestMapping(path = arrayOf("/translate/data"), produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun translateData(@RequestParam("article") articleTitle: String): Article {
        return Article(articleTitle.replace('_', ' '), mongoMan.findAllByArticle(articleTitle))
    }

    @RequestMapping(path = arrayOf("/translate/save"), method = arrayOf(RequestMethod.POST))
    fun saveTranslation(@RequestParam translation: String,
                        @RequestParam("id") originalId: String,
                        @RequestParam author: String): ResponseEntity<String> {
        println(translation);
        val toBeUpdate = mongoMan.findById(originalId)?.update(listOf(TranslationWrapper(translation, author, Date())))
        if(toBeUpdate != null) {
            mongoMan.save(toBeUpdate)
            return ResponseEntity(HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}
