package com.strange.sharedtranslate.services

import com.strange.sharedtranslate.entities.TextTranslationWrapper
import com.strange.sharedtranslate.exceptions.EntityNotFoundException
import com.strange.sharedtranslate.repository.RepositoryActions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Manager Service implementation
 *
 * Created by Notebook on 02.05.2016.
 */
@Component
class MongoManager @Autowired constructor(val repository: RepositoryActions): ManagerService {

    override fun create(created: TextTranslationWrapper): TextTranslationWrapper {
        return repository.save(created.copy())
    }

    override fun delete(id: String): TextTranslationWrapper {
        val deleted: TextTranslationWrapper? = findById(id)
        if(deleted != null) {
            return repository.delete(deleted)
        } else {
            throw EntityNotFoundException("Sorry we can't found entity with id $id")
        }
    }

    override fun findAllByArticle(article: String): List<TextTranslationWrapper> {
        return repository.findAllByArticle(article);
    }

    override fun findById(id: String): TextTranslationWrapper? {
        return repository.findOne(id);
    }

    override fun update(updated: TextTranslationWrapper): TextTranslationWrapper {
        val toBeSaved = repository.findOne(updated.id)?.update(updated.translations)
        if(toBeSaved != null) {
            return repository.save(toBeSaved);
        } else {
            throw EntityNotFoundException("Entity with id ${updated.id} was not found for updating")
        }
    }
}