package com.sample.demo.repository

import com.sample.demo.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository:CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findByPasswd(passwd: String): User?
}