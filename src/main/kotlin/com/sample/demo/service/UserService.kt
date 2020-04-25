package com.sample.demo.service

import com.sample.demo.entity.User

interface UserService {
    fun getByEmail(email: String): User?
    fun createUser(user: User)
}