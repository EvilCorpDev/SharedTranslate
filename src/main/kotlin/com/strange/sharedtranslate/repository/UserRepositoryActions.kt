package com.strange.sharedtranslate.repository

import com.strange.sharedtranslate.entities.User
import org.springframework.data.repository.Repository

/**
 * Created by Zakhar_Kliap on 16-May-16.
 */
interface UserRepositoryActions : Repository<User, String> {

    fun findOneById(id: String): User?

    fun findOneByEmail(email: String): User?

    fun save(toBeSaved: User): User

    fun delete(toBeDelete: User): User
}