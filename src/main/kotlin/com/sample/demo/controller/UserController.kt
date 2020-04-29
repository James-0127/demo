package com.sample.demo.controller

import com.sample.demo.dto.UserDto
import com.sample.demo.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {
    // ResponseBody + DTO
    @PostMapping("/signup")
    fun signUp(model: Model,
               @RequestParam("name", required = true) name: String,
               @RequestParam("email", required = true) email: String,
               @RequestParam("passwd", required = true) passwd: String
    ): String {
        val user = userService.createUser(name, email, passwd)

        model["title"] = "Welcome to Demo Project"
        model["email"] = email
        model["name"] = name

        return "welcome/" + user.id
    }

    @PostMapping("/signin")
    fun signIn(
               model: Model,
               session: HttpSession,
               @RequestParam("id", required = true) email: String,
               @RequestParam("passwd", required = true) passwd: String
    ): String {

        return try {
            val user = userService.signIn(email, passwd)

            session.setAttribute("id", user.id)
            session.setAttribute("email", user.email)
            session.setAttribute("name", user.name)

            model["title"] = "Welcome"
            model["name"] = user.name
            model["email"] = user.email

            "redirect:/welcome/" + user.id
        } catch (e:Exception) {
            e.printStackTrace()

//            model["title"] = "Error - Sign In"
            "error"
        }
    }

    @PutMapping("/name", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseBody
    fun updateName(model: Model,
                   @RequestBody userDto: UserDto
    ): ResponseEntity<*> {

        return try {
            println(userDto.toString())
            userService.updateName(userDto.email, userDto.name)
            ResponseEntity.ok(userDto.name)

        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity("error", HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }


    @DeleteMapping()
    @ResponseBody
    fun remove(model: Model,
               @RequestBody userDto: UserDto
    ): ResponseEntity<*> {

        return try {
            userService.deleteUser(userDto.email, userDto.passwd)
            model["title"] = "index"
            ResponseEntity.ok("/")

        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity("error", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}