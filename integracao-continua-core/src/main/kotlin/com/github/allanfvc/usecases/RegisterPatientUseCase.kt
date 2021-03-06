package com.github.allanfvc.usecases

import com.github.allanfvc.entities.Address
import com.github.allanfvc.entities.Patient
import java.time.LocalDate

interface RegisterPatientUseCase {
    fun register(cpf: String, name: String, birthDay: LocalDate, address: Address): Patient

    fun getPatientByCpf(cpf: String): Patient?
}