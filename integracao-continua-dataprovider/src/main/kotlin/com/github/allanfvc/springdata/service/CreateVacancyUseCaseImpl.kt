package com.github.allanfvc.springdata.service

import com.github.allanfvc.entities.VaccinationSite
import com.github.allanfvc.entities.VaccineSchedule
import com.github.allanfvc.springdata.entity.VaccineScheduleEntity
import com.github.allanfvc.springdata.repository.VaccineScheduleJpaRepository
import com.github.allanfvc.usecases.CreateVacancyUseCase
import java.time.LocalDate

class CreateVacancyUseCaseImpl(
    private val repository: VaccineScheduleJpaRepository): CreateVacancyUseCase {
    override fun createVaccinationSiteSchedule(
        vaccinationSite: VaccinationSite,
        vacancyNumber: Int,
        date: LocalDate
    ): List<VaccineSchedule> {
        val start = date.atStartOfDay()
        val end = date.atTime(23,59,59)
        val existingVacancies = repository
            .findByVaccinationSiteIdAndDate(vaccinationSite.id!!, start, end)
            .map { it.toModel() }
        val newVacancies = mutableListOf<VaccineSchedule>()
        for(i in 1..vacancyNumber) {
            newVacancies.add(VaccineSchedule(-1, vaccinationSite, null, start, false))
        }
        vaccinationSite.today = date
        newVacancies.addAll(existingVacancies)
        vaccinationSite.todaySchedule = newVacancies
        val savedVacancies = repository.saveAll(newVacancies.map { VaccineScheduleEntity(it) })
        return savedVacancies.map { it.toModel() }
    }
}