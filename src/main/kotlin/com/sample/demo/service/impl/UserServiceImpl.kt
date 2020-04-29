package com.sample.demo.service.impl

import com.sample.demo.entity.User
import com.sample.demo.repository.UserRepository
import com.sample.demo.service.UserService
import com.sample.demo.util.Crypto
import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession
import javax.transaction.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun signIn(email: String, passwd: String): User {
        val user = userRepository.findByEmail(email) ?: throw IllegalArgumentException()

        if ( !Crypto().sha256(passwd).equals(user.passwd) ) {
            throw IllegalAccessError()
        }

        return user
    }


    // p, i
    @Transactional
    override fun createUser(name: String, email: String, passwd: String): User {

        val passwdHash = Crypto().sha256(passwd)
        println(passwdHash)

        return userRepository.save(User(0, name, email, passwdHash))
    }

    @Transactional
    override fun updateName(email: String, name: String) {
        userRepository.findByEmail(email)?.updateUserName(name) ?: throw IllegalArgumentException()
    }

    @Transactional
    override fun deleteUser(email: String, passwd: String) {

        val user = userRepository.findByEmail(email) ?: throw IllegalArgumentException()

        if ( !Crypto().sha256(passwd).equals(user.passwd) ) {
            throw IllegalAccessError()
        }

        userRepository.delete(user)
    }


}