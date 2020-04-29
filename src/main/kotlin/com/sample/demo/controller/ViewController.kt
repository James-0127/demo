package com.sample.demo.controller

import com.sample.demo.entity.User
import com.sample.demo.repository.UserRepository
import com.sample.demo.util.Crypto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.thymeleaf.TemplateEngine
import java.lang.Exception
import javax.persistence.Id
import javax.servlet.http.HttpSession

@Controller
@PropertySource("classpath:/meta.yaml")
class ViewController {

    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = "Index"

        return "index"
    }

    @GetMapping("/sign/{formType}")
    fun getSign(model: Model, @PathVariable formType: String): String {

        var response: String?=null

        println(response)

        if (formType.equals("in")) {
            response = "signin"
        } else if (formType.equals("up")) {
            response = "signup"
        } else {
            response = "error"
        }

        model["title"] = response

        return response
    }

    @GetMapping("/welcome/{id}")
    fun getWelcome(model: Model,
                   @PathVariable id: Int,
                   session: HttpSession): String {

//        println("PathVariable id: $id")

        return try {
            val email = session.getAttribute("email")
            val name = session.getAttribute("name")
//            val userId = session.getAttribute("id")

            model["title"] = "Welcome"
            model["name"] = name
            model["email"] = email

            "welcome"

        } catch (e: Exception) {
            e.printStackTrace()
            model["exception"] = e.printStackTrace()
            "error"
        }
    }

    @GetMapping("/error")
    fun getError(model: Model) {
        model["title"] = "Error"
    }
}