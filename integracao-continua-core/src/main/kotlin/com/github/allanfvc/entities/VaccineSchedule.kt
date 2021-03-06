package com.github.allanfvc.entities

import java.time.LocalDateTime

class VaccineSchedule(
    val id: Int,
    val vaccinationSite: VaccinationSite,
    var patient: Patient?,
    val dateTime: LocalDateTime,
    val realized: Boolean = false) {
}