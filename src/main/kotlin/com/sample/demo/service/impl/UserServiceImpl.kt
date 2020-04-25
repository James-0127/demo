package com.sample.demo.service.impl

import com.sample.demo.entity.User
import com.sample.demo.repository.UserRepository
import com.sample.demo.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun getByEmail(email: String): User? {
        TODO("Not yet implemented")
    }

    override fun createUser(user: User) {
        userRepository.save(user)
    }

}