package com.strange.sharedtranslate.utils

import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Created by Zakhar_Kliap on 12-May-16.
 */
object Passwords {

    fun hash(pass: String): String {
        val spec = PBEKeySpec(pass.toCharArray(), getNextSalt(), 65536, 128)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val hash = factory.generateSecret(spec).encoded
        return Base64.getEncoder().encodeToString(hash)
    }

    private fun getNextSalt(): ByteArray {
        val salt = byteArrayOf()
        val random = Random()
        random.nextBytes(salt)
        return salt
    }
}
