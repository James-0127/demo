package com.sample.demo.controller

import com.sample.demo.entity.User
import com.sample.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import javax.persistence.Id

@Controller
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
}