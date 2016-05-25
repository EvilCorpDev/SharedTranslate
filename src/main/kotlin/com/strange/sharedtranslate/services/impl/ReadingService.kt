package com.strange.sharedtranslate.services.impl

import com.strange.sharedtranslate.entities.TextTranslationWrapper
import com.strange.sharedtranslate.services.ReadingServiceActions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service

/**
 * Service for forming reading text
 *
 * Created by Zakhar_Kliap on 24-May-16.
 */
@Service
@PropertySource("classpath:settings.properties")
class ReadingService @Autowired constructor(val repo: TranslationMongoService) : ReadingServiceActions {

    @Value("\${reading.pagesize}") lateinit var pageSize: String
    @Value("\${reading.translationLimit}") lateinit var translationLimit: String
    @Value("\${reading.symbolsPerPage}") lateinit var symbolsPerPage: String
    @Value("\${reading.diffInSymbols}") lateinit var diffInSymbols: String

    override fun buildArticleTranslate(article: String): List<TextTranslationWrapper> {
        val allSenteces = repo.findAllByArticle(article)
        return sortTranslations(allSenteces)
    }

    override fun buildArticleTranslateWithPage(article: String, lastSentence: Int): List<TextTranslationWrapper> {
        val sentencesOnPage = sortTranslations(repo.findByArticleWithPage(article, lastSentence / pageSize.toInt() + 1, pageSize.toInt()))
        return getListAccordingToSymbols(sentencesOnPage, article)
    }

    private fun sortTranslations(sentences: List<TextTranslationWrapper>): List<TextTranslationWrapper> {
        return sentences.map { it.copy(translations = it.translations.sortedBy { it.rating }.take(translationLimit.toInt())) }.toList()
    }

    private fun getListAccordingToSymbols(sentences: List<TextTranslationWrapper>, article: String): List<TextTranslationWrapper> {
        val symbolsCount = sentences.sumBy { it.translations[0].translation.length }
        val symbolsPage = symbolsPerPage.toInt()
        val diff = diffInSymbols.toInt()
        return when {
            symbolsPage - symbolsCount <= diff && symbolsPage - symbolsCount > 0 -> sentences
            symbolsPage - symbolsCount < 0 -> getListAccordingToSymbols(sentences.take(sentences.size - 1), article)
            else -> getExtraSentences(sentences, article, symbolsPage - symbolsCount)
        }
    }

    private fun getExtraSentences(sentences: List<TextTranslationWrapper>, article: String, symbolsLeft: Int): List<TextTranslationWrapper> {
        val lastSentence = sentences.last().number
        val sortedSentences = sortTranslations(repo.findByArticleWithPage(article, lastSentence / pageSize.toInt() + 1, pageSize.toInt()))
        val sentenceLength = sortedSentences.map { it.translations[0].translation.length }
        for(i in sentenceLength.indices) {
            if(sentenceLength.take(i).sum() > symbolsLeft) {
                return sentences.plus(sortedSentences.take(i))
            }
        }
        return emptyList()
    }
}