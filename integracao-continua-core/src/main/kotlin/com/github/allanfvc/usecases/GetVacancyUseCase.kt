package com.github.allanfvc.usecases

import com.github.allanfvc.entities.VaccineSchedule
import java.time.LocalDate

interface GetVacancyUseCase {
    fun getVacancies(vaccinationSiteId: Int, date: LocalDate): List<VaccineSchedule>
}