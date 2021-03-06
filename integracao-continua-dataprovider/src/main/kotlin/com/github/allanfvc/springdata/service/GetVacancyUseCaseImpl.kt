package com.github.allanfvc.springdata.service

import com.github.allanfvc.entities.VaccineSchedule
import com.github.allanfvc.springdata.repository.VaccineScheduleJpaRepository
import com.github.allanfvc.usecases.GetVacancyUseCase
import java.sql.Date
import java.time.LocalDate

class GetVacancyUseCaseImpl(private val repository: VaccineScheduleJpaRepository): GetVacancyUseCase {
    override fun getVacancies(vaccinationSiteId: Int, date: LocalDate): List<VaccineSchedule> {
        val start = date.atStartOfDay()
        val end = date.atTime(23,59,59)
        return repository
            .findByVaccinationSiteIdAndDate(vaccinationSiteId, start, end)
            .map { it.toModel() }
    }
}