package com.strange.sharedtranslate.services

import com.strange.sharedtranslate.entities.TextTranslationWrapper

/**
 * Actions for reading service
 *
 * Created by Zakhar_Kliap on 24-May-16.
 */
interface ReadingServiceActions {

    fun buildArticleTranslate(article: String): List<TextTranslationWrapper>

    fun buildArticleTranslateWithPage(article: String, lastSentence: Int): List<TextTranslationWrapper>
}