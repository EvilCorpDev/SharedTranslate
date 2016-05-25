package com.strange.sharedtranslate.repository

import com.strange.sharedtranslate.entities.TextTranslationWrapper
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Supported actions for working with repository
 *
 * Created by Notebook on 02.05.2016.
 */
interface TranslationRepositoryActions : PagingAndSortingRepository<TextTranslationWrapper, String> {

    override fun delete(deleted: TextTranslationWrapper)

    fun findByArticle(article: String): List<TextTranslationWrapper>

    fun findByArticle(article: String, sorter: Sort): List<TextTranslationWrapper>

    fun findByArticle(article: String, pageable: Pageable): List<TextTranslationWrapper>

    override fun findOne(id: String): TextTranslationWrapper?
}