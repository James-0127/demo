package com.sample.demo.controller

import com.sample.demo.entity.User
import com.sample.demo.service.UserService
import com.sample.demo.util.Crypto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {

    @PostMapping("/signup")
    fun signUp(model: Model,
               @RequestParam("name", required = true, defaultValue = "test") name: String,
               @RequestParam("email", required = true, defaultValue = "test@test.com") email: String,
               @RequestParam("passwd", required = true, defaultValue = "1234") passwd: String
    ): String {

        val passwdHash = Crypto().sha256(passwd)
        println(passwdHash)

        val user = userService.createUser(User(0, name, email, passwdHash))
        println(user.toString())

        model["title"] = "Welcome to Demo Project"
        model["name"] = name

        return "welcome"
    }

    @PostMapping("/signin")
    fun signIn(model: Model,
               session: HttpSession,
               @RequestParam("email", required = true, defaultValue = "test@test.com") email: String,
               @RequestParam("passwd", required = true, defaultValue = "1234") passwd: String
    ): String {

        val inputPasswdHash = Crypto().sha256(passwd)

        try {
            val user = userService.findByEmail(email)

            if (user != null) {
                if (inputPasswdHash.equals(user.passwd)) {
                    session.setAttribute("email", user.email)
                    model["title"] = "Welcome to Demo Project"
                    model["name"] = user.name

                    return "welcome"
                } else {
                    model["title"] = "Error"
                    return "error"
                }
            } else {
                throw IllegalAccessError()
            }
        } catch (e:Exception) {
            e.printStackTrace()

            model["title"] = "Error"
            return "error"
        }
    }

}