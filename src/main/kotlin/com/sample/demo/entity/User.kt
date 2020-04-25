package com.sample.demo.entity

import javax.persistence.*

@Entity
@Table(name = "user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Long = 0,

        @Column(name = "name")
        var name: String = "",

        @Column(name = "email")
        var email: String = "",

        @Column(name = "passwd")
        var passwd: String = ""
) {

}