package com.strange.sharedtranslate.services

import com.strange.sharedtranslate.entities.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Created by Zakhar_Kliap on 17-May-16.
 */
interface ArticleService {

    fun save(created: Article): Article

    fun delete(id: String)

    fun findByTitle(article: String): Article

    fun findById(id: String): Article?

    fun findAll(pageable: Pageable): Page<Article>

    fun update(updated: Article): Article
}