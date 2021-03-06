package com.github.allanfvc.springweb.dto

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class CreateVacancyDTO(
    val date: Date,
    val vacancies: Int
) {
    fun toLocalDate(): LocalDate {
        return Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }
}

