package com.github.allanfvc.springweb.dto

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class RegisterPatientDTO(
    val cpf: String,
    val name: String,
    val birthDay: Date,
    val addressName: String,
    val addressDistrict: String,
    val addressCity: String,
    val addressState: String,
    val addressZipCode: String,
) {
    fun convertBirthDayToLocalDate(): LocalDate {
        return Instant.ofEpochMilli(birthDay.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    fun fullAddress(): String {
        return "$addressZipCode, $addressName, $addressCity,$addressState"
    }
}
