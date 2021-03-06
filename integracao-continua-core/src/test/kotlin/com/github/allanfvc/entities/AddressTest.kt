package com.github.allanfvc.entities

import com.github.allanfvc.enums.Uf
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AddressTest {
  
    private val location = Location(-4.9679524, -39.0281583)

    @Test
    fun `The address need its name`() {
        val testAddress = Address("", "Itaperi", "Fortaleza", Uf.CE, "60743-670", -1, location)
        Assertions.assertEquals(testAddress.isValid(), false)
    }

    @Test
    fun `The address need its district`() {
        val testAddress = Address("Rua Professora Stella Cochrane", "", "Fortaleza", Uf.CE, "60743-670",-1, location)
        Assertions.assertEquals(testAddress.isValid(), false)
    }

    @Test
    fun `The address need its city`() {
        val testAddress = Address("Rua Professora Stella Cochrane", "Itaperi", "", Uf.CE, "60743-670", -1, location)
        Assertions.assertEquals(testAddress.isValid(), false)
    }

    @Test
    fun `The address need its zipcode to match XXXXX-XXX pattern`() {
        val testAddress = Address("Rua Professora Stella Cochrane", "Itaperi", "Fortaleza", Uf.CE, "60743670", -1, location)
        Assertions.assertEquals(testAddress.isValid(), false)
    }
}