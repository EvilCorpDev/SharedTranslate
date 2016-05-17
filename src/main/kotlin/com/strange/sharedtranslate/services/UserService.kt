package com.strange.sharedtranslate.services

import com.strange.sharedtranslate.entities.User

/**
 * Created by Zakhar_Kliap on 16-May-16.
 */
interface UserService {

    fun save(created: User): User

    fun delete(id: String): User

    fun findOneById(id: String): User?

    fun findOneByEmail(email: String): User?

    fun update(updated: User): User
}