package com.strange.sharedtranslate.services.impl

import com.strange.sharedtranslate.entities.TextTranslationWrapper
import com.strange.sharedtranslate.exceptions.EntityNotFoundException
import com.strange.sharedtranslate.repository.TranslationRepositoryActions
import com.strange.sharedtranslate.services.TranslationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

/**
 * Manager Service implementation
 *
 * Created by Notebook on 02.05.2016.
 */
@Service
class TranslationMongoService @Autowired constructor(val repository: TranslationRepositoryActions): TranslationService {

    override fun save(created: TextTranslationWrapper): TextTranslationWrapper {
        return repository.save(created.copy())
    }

    override fun delete(id: String) {
        val deleted: TextTranslationWrapper? = findOneById(id)
        if(deleted != null) {
            repository.delete(deleted)
        } else {
            throw EntityNotFoundException("Sorry we can't found entity with id $id")
        }
    }

    override fun findAllByArticle(article: String): List<TextTranslationWrapper> {
        return repository.findByArticle(article, Sort("number"));
    }

    override fun findByArticleWithPage(article: String, pageNumber: Int, pageSize: Int): List<TextTranslationWrapper> {
        return repository.findByArticle(article, PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC, "number"))
    }

    override fun findOneById(id: String): TextTranslationWrapper? {
        return repository.findOne(id);
    }

    override fun update(updated: TextTranslationWrapper): TextTranslationWrapper {
        if(updated.id == null) {
            throw EntityNotFoundException("Entity with null id")
        }
        val toBeSaved = repository.findOne(updated.id)?.update(updated.translations)
        if(toBeSaved != null) {
            return repository.save(toBeSaved);
        } else {
            throw EntityNotFoundException("Entity with id ${updated.id} was not found for updating")
        }
    }
}