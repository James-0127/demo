package com.sample.demo.service

import com.sample.demo.entity.User

interface UserService {
    fun findByEmail(email: String): User?
    fun createUser(user: User)
    fun updateName(email: String, name: String)
    fun deleteUser(email: String, passwd: String): String
}