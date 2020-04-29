package com.sample.demo.dto

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable

data class UserDto(
    var id: Long = 0,

    var name: String = "",

    var email: String = "",

    var passwd: String = ""
)
//) : Serializable {
//
//    constructor(userDto: UserDto) : this(
//            id = userDto.id,
//            name = userDto.name,
//            email = userDto.email,
//            passwd = userDto.passwd
//    )
//}