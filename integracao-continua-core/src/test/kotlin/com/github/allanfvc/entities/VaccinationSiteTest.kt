package com.github.allanfvc.entities

import com.github.allanfvc.enums.Uf
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.assertFailsWith

class VaccinationSiteTest {
    private val location = Location(-4.9679524, -39.0281583)
    private var testAddress = Address("Rua Professora Stella Cochrane", "Itaperi", "Fortaleza", Uf.CE, "60743-670", -1, location)

    @Test
    fun `no more than 100 vaccinations can occur at the same location on the same day `() {
        val vaccinationSite = VaccinationSite("Test Vaccination Site", testAddress,-1)
        val todaySchedule = mutableListOf<VaccineSchedule>()
        val todayAppliedVaccines = mutableListOf<AppliedVaccine>()
        for(i in 1..50) {
            val patient = Patient("Patient Teste $i", LocalDate.now().minusYears(66), testAddress, "999999999-99")
            todaySchedule.add(VaccineSchedule(-1,vaccinationSite, patient, LocalDateTime.now().minusMinutes(i.toLong())))
        }
        for(i in 1..51) {
            val patient = Patient("Patient Teste $i", LocalDate.now().minusYears(66), testAddress, "999999999-99")
            todayAppliedVaccines.add(AppliedVaccine(vaccinationSite, patient, LocalDateTime.now().minusSeconds(i.toLong())))
        }
        vaccinationSite.todaySchedule = todaySchedule
        val exception = assertFailsWith<Exception> {
            vaccinationSite.todayAppliedVaccines = todayAppliedVaccines
        }
        Assertions.assertEquals(exception.message, "The amount of vaccines exceed the maximum allowed.")
    }

    @Test
    fun `cannot create a vaccination site with a invalid address`() {
        val exception = assertFailsWith<Exception> {
            val location = Location(-4.9679524, -39.0281583)
            val exceptionAddress = Address("","", "",Uf.CE, "63800000", -1, location)
            VaccinationSite("Test Vaccination Site", exceptionAddress,-1)
        }
        Assertions.assertEquals(exception.message, "Cannot create a vaccination site with a invalid address.")
    }
}