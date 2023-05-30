package com.example.hastanerandevusistemi.register


data class Register(
    var userId: Int,
    var name: String,
    var surname: String,
    var tc: String,
    var gender: String,
    var birthday: String,
    var email: String,
    var phone: String,
    var password: String
) : java.io.Serializable {
    fun toRegisterEntity(): RegisterEntity {
        return RegisterEntity(
            name = name,
            surname = surname,
            TC = tc,
            gender = gender,
            birthday = birthday,
            email = email,
            phone = phone,
            password = password,
        )
    }
}