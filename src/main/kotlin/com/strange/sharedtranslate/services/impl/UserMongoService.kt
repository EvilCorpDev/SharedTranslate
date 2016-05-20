package com.strange.sharedtranslate.services.impl

import com.strange.sharedtranslate.entities.User
import com.strange.sharedtranslate.exceptions.EntityNotFoundException
import com.strange.sharedtranslate.repository.UserRepositoryActions
import com.strange.sharedtranslate.services.UserService
import com.strange.sharedtranslate.utils.Passwords
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Zakhar_Kliap on 16-May-16.
 */
@Component
class UserMongoService @Autowired constructor(val userActions: UserRepositoryActions) : UserService {

    override fun findOneByLogin(login: String) = userActions.findOneByLogin(login)

    override fun save(created: User) = userActions.save(created.copy(password = Passwords.hash(created.password, created.salt)))

    override fun delete(id: String): User {
        val toBeDelete = findOneById(id);
        if (toBeDelete != null) {
            return userActions.delete(toBeDelete)
        } else {
            throw EntityNotFoundException("User with id $id was not found.")
        }
    }

    override fun findOneById(id: String) = userActions.findOneById(id)

    override fun findOneByEmail(email: String) = userActions.findOneByEmail(email)

    override fun update(updated: User) = save(updated)

    override fun checkUser(login: String, pass: String): User? {
        val user = userActions.findOneByLogin(login)
        if(user == null || user.password != Passwords.hash(pass, user.salt)) {
            return null
        } else {
            return user
        }
    }

}