package com.github.allanfvc.entities

import java.time.LocalDateTime

class AppliedVaccine(
    val vaccinationSite: VaccinationSite,
    val patient: Patient,
    val dateTime: LocalDateTime
) {
}