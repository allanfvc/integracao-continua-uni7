package com.github.allanfvc.springdata.repository

import com.github.allanfvc.springdata.entity.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AddressJpaRepository: JpaRepository<AddressEntity, Int> {
    fun findFirstByNameAndDistrictAndCityAndStateAndZipCode(name: String, district: String, city: String, state: String, zipCode: String): AddressEntity?
}