package com.github.allanfvc.springdata.service

import com.github.allanfvc.entities.Address
import com.github.allanfvc.entities.VaccinationSite
import com.github.allanfvc.springdata.entity.AddressEntity
import com.github.allanfvc.springdata.entity.VaccinationSiteEntity
import com.github.allanfvc.springdata.repository.AddressJpaRepository
import com.github.allanfvc.springdata.repository.VaccinationSiteJpaRepository
import com.github.allanfvc.usecases.RegisterVaccinationSiteUseCase

class RegisterVaccinationSiteUseCaseImpl(
    private val jpa: VaccinationSiteJpaRepository,
    private val jpaAddress: AddressJpaRepository
    ):RegisterVaccinationSiteUseCase {
    override fun register(name: String, address: Address): VaccinationSite {
        var entity = VaccinationSiteEntity(name = name,address = validateAddress(address))
        entity = jpa.save(entity)!!
        return entity.toModel()
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