package com.github.allanfvc.usecases

import com.github.allanfvc.entities.Address
import com.github.allanfvc.entities.VaccinationSite

interface RegisterVaccinationSiteUseCase {
    fun register(name: String, address: Address): VaccinationSite
}