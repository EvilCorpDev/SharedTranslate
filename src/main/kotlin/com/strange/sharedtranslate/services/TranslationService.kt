package com.strange.sharedtranslate.services

import com.strange.sharedtranslate.entities.TextTranslationWrapper

/**
 * Service actions for working with entities
 *
 * Created by Notebook on 02.05.2016.
 */
interface TranslationService {

    fun save(created: TextTranslationWrapper): TextTranslationWrapper

    fun delete(id: String)

    fun findAllByArticle(article: String): List<TextTranslationWrapper>

    fun findByArticleWithPage(article: String, pageNumber: Int, pageSize: Int): List<TextTranslationWrapper>

    fun findOneById(id: String): TextTranslationWrapper?

    fun update(updated: TextTranslationWrapper): TextTranslationWrapper
}