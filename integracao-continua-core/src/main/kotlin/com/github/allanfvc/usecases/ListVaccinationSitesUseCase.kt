package com.github.allanfvc.usecases

import com.github.allanfvc.entities.Patient
import com.github.allanfvc.entities.VaccinationSite

interface ListVaccinationSitesUseCase  {
    fun list(patient: Patient): List<VaccinationSite>
}