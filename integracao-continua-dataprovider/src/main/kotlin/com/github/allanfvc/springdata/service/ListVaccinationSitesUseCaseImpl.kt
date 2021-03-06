package com.github.allanfvc.springdata.service

import com.github.allanfvc.entities.Patient
import com.github.allanfvc.entities.VaccinationSite
import com.github.allanfvc.springdata.repository.VaccinationSiteJpaRepository
import com.github.allanfvc.usecases.ListVaccinationSitesUseCase

class ListVaccinationSitesUseCaseImpl(
    private val jpa: VaccinationSiteJpaRepository
    ): ListVaccinationSitesUseCase {

    override fun list(patient: Patient): List<VaccinationSite> {
        val list = jpa.findAll()
        val sites = list.map { VaccinationSite(it.name, it.address.toModel(), it.id) }
        sites.forEach {
            it.distance = patient.getDistance(it.address)
        }
        return sites.sortedBy { it.distance }
    }
}