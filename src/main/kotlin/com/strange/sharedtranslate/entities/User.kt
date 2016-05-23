package com.strange.sharedtranslate.entities

/**
 * User model data class
 *
 * Created by Zakhar_Kliap on 12-May-16.
 */
data class User (val id: String?, val name: String, val login: String,
                 val email: String, val password: String, val salt: String, val numberOfCredits: Int = 0) {

    constructor() : this("", "", "", "", "", "")
}