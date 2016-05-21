package com.strange.sharedtranslate.services

/**
 * Created by Notebook on 21.05.2016.
 */
interface RatingServiceActions {

    fun changeRate(authorLogin: String, commentedBy: String, originalId: String, increase: Boolean)
}