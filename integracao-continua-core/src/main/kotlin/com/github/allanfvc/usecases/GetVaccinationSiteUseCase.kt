package com.github.allanfvc.usecases

import com.github.allanfvc.entities.VaccinationSite
import java.util.*

interface GetVaccinationSiteUseCase {
    fun getVaccinationSiteById(id: Int): VaccinationSite?
}