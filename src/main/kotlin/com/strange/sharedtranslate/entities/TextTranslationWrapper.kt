package com.strange.sharedtranslate.entities

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Entity that contains original text and different translations
 *
 * Created by Notebook on 02.05.2016.
 */
@Document(collection = "article-texts")
data class TextTranslationWrapper(val article: String = "", val original: String = "", val number: Int = 0,
                                  val translations: List<TranslationWrapper> = emptyList(), val id: String? = null) {

    fun update(newTranslations: List<TranslationWrapper>): TextTranslationWrapper {
        val notUpdated = translations.filter { !newTranslations.map { it.author }.contains(it.author) }
        val updatedTranslations = notUpdated.plus(newTranslations).sortedBy { it.translationDate }.toList()
        return this.copy(translations = updatedTranslations)
    }
}