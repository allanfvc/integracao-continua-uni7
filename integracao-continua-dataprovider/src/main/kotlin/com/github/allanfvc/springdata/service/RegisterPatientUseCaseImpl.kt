package com.github.allanfvc.springdata.service

import com.github.allanfvc.entities.Address
import com.github.allanfvc.entities.Patient
import com.github.allanfvc.springdata.entity.AddressEntity
import com.github.allanfvc.springdata.entity.PatientEntity
import com.github.allanfvc.springdata.repository.AddressJpaRepository
import com.github.allanfvc.springdata.repository.PatientJpaRepository
import com.github.allanfvc.usecases.RegisterPatientUseCase
import java.time.LocalDate

class RegisterPatientUseCaseImpl(
    private val jpa: PatientJpaRepository,
    private val jpaAddress: AddressJpaRepository
) : RegisterPatientUseCase {
    override fun register(cpf: String, name: String, birthDay: LocalDate, address: Address): Patient {

        var entity = PatientEntity(cpf, name, birthDay, validateAddress(address))
        entity = jpa.save(entity)!!
        return entity.toModel()
    }

    override fun getPatientByCpf(cpf: String): Patient? {
        return jpa.findByCpf(cpf)?.toModel()
    }

    private fun validateAddress(address: Address): AddressEntity {

        return address.let {
            jpaAddress
                .findFirstByNameAndDistrictAndCityAndStateAndZipCode(
                    it.name, it.district, it.city, it.state.abbreviation, it.zipCode
                )
        } ?: jpaAddress.save(AddressEntity(address))!!
    }

}