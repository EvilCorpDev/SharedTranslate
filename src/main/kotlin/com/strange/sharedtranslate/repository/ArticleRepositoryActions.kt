package com.strange.sharedtranslate.repository

import com.strange.sharedtranslate.entities.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by Zakhar_Kliap on 17-May-16.
 */
interface ArticleRepositoryActions : PagingAndSortingRepository<Article, String> {

    override fun delete(deleted: Article)

    fun findByTitle(article: String): Article

    fun findOneById(id: String): Article?

    override fun findAll(): List<Article>

    override fun findAll(pageable: Pageable): Page<Article>

    fun save (saved: Article): Article
}