package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.Article
import com.strange.sharedtranslate.entities.TextTranslationWrapper
import com.strange.sharedtranslate.repository.MongoManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

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

    fun saveTranslation() {

    }
}
