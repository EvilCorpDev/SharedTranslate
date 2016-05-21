package com.strange.sharedtranslate.services.impl

import com.strange.sharedtranslate.entities.Article
import com.strange.sharedtranslate.exceptions.EntityNotFoundException
import com.strange.sharedtranslate.repository.ArticleRepositoryActions
import com.strange.sharedtranslate.services.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

/**
 * Created by Zakhar_Kliap on 17-May-16.
 */
@Service
class ArticleMongoService @Autowired constructor(val repo: ArticleRepositoryActions) : ArticleService {

    override fun save(created: Article) = repo.save(created.copy())

    override fun delete(id: String) {
        val toBeDelete = findById(id)
        if (toBeDelete != null) {
            repo.delete(toBeDelete)
        } else {
            throw EntityNotFoundException("Entity with id $id was not found")
        }
    }

    override fun findByTitle(article: String) = repo.findByTitle(article)

    override fun findById(id: String) = repo.findOneById(id)

    override fun findAll(pageable: Pageable) = repo.findAll(pageable)

    override fun update(updated: Article) = save(updated)

}