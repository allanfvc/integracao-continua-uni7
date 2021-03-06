package com.github.allanfvc.springdata.service

import com.github.allanfvc.entities.VaccinationSite
import com.github.allanfvc.springdata.repository.VaccinationSiteJpaRepository
import com.github.allanfvc.usecases.GetVaccinationSiteUseCase

class GetVaccinationSiteUseCaseImpl(
    private val repository: VaccinationSiteJpaRepository): GetVaccinationSiteUseCase {
    override fun getVaccinationSiteById(id: Int): VaccinationSite? {
        val entity = repository.findById(id).orElse(null)
        if(entity != null) {
            return entity.toModel();
        }
        return null
    }
}