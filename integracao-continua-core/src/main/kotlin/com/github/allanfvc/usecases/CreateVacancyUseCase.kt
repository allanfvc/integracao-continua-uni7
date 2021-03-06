package com.github.allanfvc.usecases

import com.github.allanfvc.entities.VaccinationSite
import com.github.allanfvc.entities.VaccineSchedule
import java.time.LocalDate

interface CreateVacancyUseCase {
    fun createVaccinationSiteSchedule(vaccinationSite: VaccinationSite, vacancyNumber: Int, date: LocalDate): List<VaccineSchedule>
}