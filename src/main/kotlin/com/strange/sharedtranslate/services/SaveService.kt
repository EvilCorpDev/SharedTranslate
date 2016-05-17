package com.strange.sharedtranslate.services

import com.strange.sharedtranslate.entities.Article
import com.strange.sharedtranslate.entities.TextTranslationWrapper
import com.strange.sharedtranslate.services.impl.ArticleMongoService
import com.strange.sharedtranslate.services.impl.TranslationMongoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File

/**
 * Service that saves text into db
 *
 * Created by Zakhar_Kliap on 04-May-16.
 */
@Component
class SaveService @Autowired constructor(val translationManager: TranslationMongoService,
                                         val articleService: ArticleMongoService) {

    private val coversPath = "covers/"

    fun saveFile(toSave: File, articleTitle: String = "") {
        when (toSave.extension) {
            "txt" -> saveTxt(toSave, articleTitle)
        }
    }

    fun saveArticle(article: Article, cover: MultipartFile?) {
        if (cover != null) {
            articleService.save(article.copy(cover = coversPath + cover.originalFilename))
        } else {
            articleService.save(article.copy())
        }
    }

    private fun saveTxt(txtFile: File, articleTitle: String) {
        val textLines = txtFile.readLines().flatMap { it.split('.') }.map { it + "." }
        println(textLines)
        for (line in textLines) {
            println(line)
            translationManager.save(TextTranslationWrapper(articleTitle, null, line, emptyList()))
        }
    }
}