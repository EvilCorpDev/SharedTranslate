package com.strange.sharedtranslate.services.impl

import com.strange.sharedtranslate.exceptions.EntityNotFoundException
import com.strange.sharedtranslate.exceptions.PersmissionDeniedException
import com.strange.sharedtranslate.services.RatingServiceActions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service

/**
 * Service for increasing ratings
 *
 * Created by Notebook on 21.05.2016.
 */
@Service
@PropertySource("classpath:settings.properties")
class RatingService @Autowired constructor(val translationService: TranslationMongoService,
                                           val userService: UserMongoService) : RatingServiceActions {

    @Value("\${rating.increase}") lateinit var rateIncrease: String

    override fun changeRate(authorLogin: String, commentedBy: String, originalId: String, increase: Boolean) {
        val translationItem = translationService.findOneById(originalId) ?:
                throw EntityNotFoundException("Sentence with id $originalId was not found")
        val authorTranslation = translationItem.translations.find { it.author == authorLogin }
        if (authorTranslation != null) {
            if (authorTranslation.commentedBy.contains(commentedBy)) {
                throw PersmissionDeniedException("User $commentedBy has already commented this translation")
            }
            changeUserRate(authorLogin, increase)
            val rate = if(increase) 1 else -1;
            translationService.save(translationItem.update(listOf(authorTranslation.update(listOf(commentedBy), rate))))
        }
    }

    private fun changeUserRate(userLogin: String, increase: Boolean) {
        if(increase) {
            val finded = userService.findOneByLogin(userLogin) ?:
                    throw EntityNotFoundException("User with login $userLogin was not found")
            userService.update(finded.copy(numberOfCredits = finded.numberOfCredits + rateIncrease.toInt()))
        }
    }
}