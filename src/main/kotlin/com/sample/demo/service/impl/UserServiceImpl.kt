package com.sample.demo.service.impl

import com.sample.demo.entity.User
import com.sample.demo.repository.UserRepository
import com.sample.demo.service.UserService
import com.sample.demo.util.Crypto
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }


    override fun createUser(user: User) {
        userRepository.save(user)
    }

    override fun updateName(email: String, name: String) {
        userRepository.findByEmail(email)?.updateUserName(name)
    }

    override fun deleteUser(email: String, passwd: String): String {

        val user = userRepository.findByEmail(email)

        if (user != null) {
            val inputPasswd = Crypto().sha256(passwd)

            if (!user.passwd.equals(inputPasswd)) {
                return "error"
            }

            userRepository.delete(user)

        } else {
            return "error"
        }

        return "index"
    }


}