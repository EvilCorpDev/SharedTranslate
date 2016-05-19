package com.strange.sharedtranslate.services

import com.cloudinary.Cloudinary
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
                                         val articleService: ArticleMongoService,
                                         val cloudinary: Cloudinary) {

    fun saveFile(toSave: File, articleTitle: String = "") {
        when (toSave.extension) {
            "txt" -> saveTxt(toSave, articleTitle)
        }
    }

    fun saveArticle(articleTitle: String, description: String, cover: MultipartFile, realPath: String) {
        val article = Article(articleTitle, "Zakhar Kliap", description = description)
        if (!cover.isEmpty) {
            val coverToSave = File(realPath + cover.originalFilename)
            cover.transferTo(coverToSave)
            val uploadResult = cloudinary.uploader().upload(coverToSave, emptyMap<String, Any>())
            articleService.save(article.copy(cover = uploadResult["secure_url"].toString()))
        } else {
            articleService.save(article.copy())
        }
    }

    private fun saveTxt(txtFile: File, articleTitle: String) {
        val textLines = txtFile.readLines().flatMap { it.split('.') }.map { it + "." }
        println(textLines)
        for ((index, line) in textLines.withIndex()) {
            println(line)
            translationManager.save(TextTranslationWrapper(articleTitle, line, index))
        }
    }
}