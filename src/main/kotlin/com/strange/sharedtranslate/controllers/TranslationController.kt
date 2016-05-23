package com.strange.sharedtranslate.controllers

import com.strange.sharedtranslate.entities.ArticleText
import com.strange.sharedtranslate.entities.TranslationWrapper
import com.strange.sharedtranslate.entities.User
import com.strange.sharedtranslate.exceptions.PermissionDeniedException
import com.strange.sharedtranslate.services.impl.RatingService
import com.strange.sharedtranslate.services.impl.TranslationMongoService
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
import javax.servlet.http.HttpSession

/**
 * Controller for translation page
 *
 * Created by Zakhar_Kliap on 27-Apr-16.
 */
@Controller
class TranslationController @Autowired constructor(val translationMan: TranslationMongoService,
                                                   val ratingService: RatingService) {

    @RequestMapping(path = arrayOf("/translate/{article}", "/translate"))
    fun translate() = "base"

    @RequestMapping("/translate/content")
    fun translateContent() = "translate-page"

    @RequestMapping(path = arrayOf("/translate/data"), produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    @ResponseBody
    fun translateData(@RequestParam("article") articleTitle: String): ArticleText {
        return ArticleText(articleTitle.replace('_', ' '), translationMan.findAllByArticle(articleTitle))
    }

    @RequestMapping("/translate/translation-template")
    fun translationTemplate() = "translation-template"

    @RequestMapping(path = arrayOf("/translate/save"), method = arrayOf(RequestMethod.POST))
    fun saveTranslation(@RequestParam translation: String,
                        @RequestParam("id") originalId: String, session: HttpSession): ResponseEntity<String> {
        val author = session.getAttribute("user") as User
        val toBeUpdate = translationMan.findOneById(originalId)?.update(listOf(TranslationWrapper(translation, author.login, Date())))
        if (toBeUpdate != null) {
            translationMan.save(toBeUpdate)
            return ResponseEntity(HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @RequestMapping(path = arrayOf("/translation/rate"), method = arrayOf(RequestMethod.POST))
    fun increaseRate(@RequestParam login: String, @RequestParam originalId: String,
                     @RequestParam increase: Boolean, session: HttpSession): ResponseEntity<String> {
        val currentUser = session.getAttribute("user") ?: throw PermissionDeniedException("There is no authorized user")
        ratingService.changeRate(login, (currentUser as User).login, originalId, increase)
        return ResponseEntity(HttpStatus.OK)
    }
}
