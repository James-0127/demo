package com.sample.demo.controller

import com.sample.demo.entity.User
import com.sample.demo.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {

    @PostMapping("/signup")
    fun signUp(model: Model,
               @RequestParam("name", required = true, defaultValue = "test") name: String,
               @RequestParam("email", required = true, defaultValue = "test@test.com") email: String,
               @RequestParam("passwd", required = true, defaultValue = "1234") passwd: String): String
    {
        val user = userService.createUser(User(0, name, email, passwd))
//        val user = repository.save(User(name, email, passwd))

        return ""
    }

}