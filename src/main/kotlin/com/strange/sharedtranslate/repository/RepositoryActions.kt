package com.strange.sharedtranslate.repository

import com.strange.sharedtranslate.entities.TextTranslationWrapper
import org.springframework.data.repository.Repository

/**
 * Supported actions for working with repository
 *
 * Created by Notebook on 02.05.2016.
 */
@org.springframework.stereotype.Repository
interface RepositoryActions: Repository<TextTranslationWrapper, String> {

    fun delete(deleted: TextTranslationWrapper): TextTranslationWrapper

    fun findByArticle(article: String): List<TextTranslationWrapper>

    fun findOne(id: String): TextTranslationWrapper?

    fun save (saved: TextTranslationWrapper): TextTranslationWrapper
}