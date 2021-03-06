package com.github.allanfvc.entities

import java.time.LocalDate
import java.time.Period

data class Patient(val name: String, val birthDay: LocalDate, val address: Address, val cpf: String) {
    var vaccinationSites: List<VaccinationSite> = listOf()

    init {
        if (birthDay.isAfter(LocalDate.now())) {
            throw Exception("Cannot create a patient that has not yet born.")
        }

        if (!address.isValid()) {
            throw Exception("Cannot create a patient with a invalid address.")
        }
    }

    fun canSchedule(): Boolean {
        val period = Period.between(birthDay, LocalDate.now());
        return period.years > 65;
    }

    fun getDistance(otherAddress: Address): Double {
        val latitude = otherAddress.location.lat
        val logitude = otherAddress.location.lng
        val userLatitude = address.location.lat;
        val userLongitude = address.location.lng;
        return Math.sqrt((latitude - userLatitude) * (latitude - userLatitude) + (logitude - userLongitude) * (logitude - userLongitude));
    }
}

