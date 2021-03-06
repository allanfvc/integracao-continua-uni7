package com.github.allanfvc.entities

import java.time.LocalDate
import java.time.LocalDateTime

data class VaccinationSite(
    val name: String,
    val address: Address,
    val id: Int?
) {
    constructor(name: String, address: Address, todayAppliedVaccines: List<AppliedVaccine>, todaySchedule: List<VaccineSchedule>): this(
        name,
        address,
        -1
    ) {
        this.todayAppliedVaccines = todayAppliedVaccines
        this.todaySchedule = todaySchedule
    }

    var distance: Double = 0.0
    var today: LocalDate = LocalDate.now()

    init {
        if (!address.isValid()) {
            throw Exception("Cannot create a vaccination site with a invalid address.")
        }
    }
    var todaySchedule: List<VaccineSchedule> = listOf()
        set(value) {
            field = value
            if(!isValid()) throw Exception("The amount of vaccines exceed the maximum allowed.")
        }
    var todayAppliedVaccines: List<AppliedVaccine> = listOf()
        set(value) {
            field = value
            if(!isValid()) throw Exception("The amount of vaccines exceed the maximum allowed.")
        }

    fun isValid(): Boolean {
        val start = today.atStartOfDay()
        val end = today.atTime(23,59,59)
        validateInvalidAppliedVaccines(start, end)
        validadeInvalidScheduleVaccines(start, end)
        val scheduledVaccineAmount = this.todaySchedule.filter { schedule -> !schedule.realized }.size
        val appliedVaccinesAmount = this.todayAppliedVaccines.size
        return (scheduledVaccineAmount + appliedVaccinesAmount) <= 100
    }

    private fun validadeInvalidScheduleVaccines(todayStart: LocalDateTime?, todayEnd: LocalDateTime?) {
        val hasInvalidScheduleVaccines = this.todaySchedule.any { schedule ->
            schedule.dateTime.isBefore(todayStart) || schedule.dateTime.isAfter(todayEnd)
        }
        if(hasInvalidScheduleVaccines) throw Exception("This vaccination site has incorrect information about scheduled vaccines.")
    }

    private fun validateInvalidAppliedVaccines(todayStart: LocalDateTime?, todayEnd: LocalDateTime?) {
        val hasInvalidAppliedVaccines = this.todayAppliedVaccines.any { appliedVaccine ->
            appliedVaccine.dateTime.isBefore(todayStart) || appliedVaccine.dateTime.isAfter(todayEnd)
        }
        if(hasInvalidAppliedVaccines) throw Exception("This vaccination site has incorrect information about applied vaccines.")
    }
}