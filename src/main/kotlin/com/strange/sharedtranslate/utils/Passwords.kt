package com.strange.sharedtranslate.utils

import org.springframework.security.crypto.keygen.KeyGenerators
import org.springframework.security.crypto.password.StandardPasswordEncoder

/**
 * Created by Zakhar_Kliap on 12-May-16.
 */
object Passwords {

    fun hash(pass: String, salt: String?): String {
        return StandardPasswordEncoder(salt).encode(pass)
    }

    fun getNextSalt(): String {
        return KeyGenerators.string().generateKey()
    }

    fun checkPass(salt: String, pass: String, encryptedPass: String): Boolean {
        return StandardPasswordEncoder(salt).matches(pass, encryptedPass)
    }
}
