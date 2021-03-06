package com.github.allanfvc.entities

import com.github.allanfvc.enums.Uf
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertFailsWith

class PatientTest {
    private val location = Location(-4.9679524, -39.0281583)
    private var testAddress = Address("Rua Professora Stella Cochrane", "Itaperi", "Fortaleza", Uf.CE, "60743-670", -1, location)
    @Test
    fun `can only request scheduling, patients over 65 years`() {
        val testDate = LocalDate.now().minusYears(66)
        val patient = Patient("Teste Paciente", testDate, testAddress, "999999999-99")
        Assertions.assertEquals(patient.canSchedule(), true)
    }

    @Test
    fun `can not request scheduling, patients under 65 years`() {
        val testDate = LocalDate.now().minusYears(18)
        val patient = Patient("Teste Paciente", testDate, testAddress, "999999999-99")
        Assertions.assertEquals(patient.canSchedule(), false)
    }

    @Test
    fun `only patients with a valid birth date can be created`() {
        val testDate = LocalDate.now().plusDays(2)
        val exception = assertFailsWith<Exception> {
            Patient("Teste Paciente", testDate, testAddress, "999999999-99")
        }
        Assertions.assertEquals(exception.message, "Cannot create a patient that has not yet born.")
    }

    @Test
    fun `cannot create a patient with a invalid address`() {
        val exception = assertFailsWith<Exception> {
            val exceptionAddress = Address("","", "",Uf.CE, "63800000", -1, location)
            Patient("Teste Paciente", LocalDate.now().minusYears(21), exceptionAddress, "999999999-99")
        }
        Assertions.assertEquals(exception.message, "Cannot create a patient with a invalid address.")
    }
}