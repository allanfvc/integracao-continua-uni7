package com.github.allanfvc.entities

import com.github.allanfvc.enums.Role

open class User(open val username: String, open val password: String, val role: Role)