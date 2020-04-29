package com.sample.demo.service

import com.sample.demo.entity.User
import javax.servlet.http.HttpSession

interface UserService {
    fun findByEmail(email: String): User?

    fun signIn(email: String, passwd: String): User

    fun createUser(name: String, email: String, passwd: String): User

    fun updateName(email: String, name: String)

    fun deleteUser(email: String, passwd: String)
}