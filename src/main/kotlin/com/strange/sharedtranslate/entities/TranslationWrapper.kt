package com.strange.sharedtranslate.entities

import java.util.*

/**
 * Entity that contains translation data about some original text
 *
 * Created by Notebook on 02.05.2016.
 */
data class TranslationWrapper(val translation: String, val author: String,
                              val translationDate: Date)