package com.strange.sharedtranslate.repository

import com.strange.sharedtranslate.entities.TextTranslationWrapper

/**
 * Service actions for working with entities
 *
 * Created by Notebook on 02.05.2016.
 */
interface ManagerService {

    fun create(created: TextTranslationWrapper): TextTranslationWrapper

    fun delete(id: String): TextTranslationWrapper

    fun findAllByArticle(article: String): List<TextTranslationWrapper>

    fun findById(id: String): TextTranslationWrapper?

    fun update(updated: TextTranslationWrapper): TextTranslationWrapper
}