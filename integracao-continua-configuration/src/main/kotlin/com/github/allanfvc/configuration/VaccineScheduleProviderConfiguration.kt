package com.github.allanfvc.configuration

import com.github.allanfvc.springdata.repository.VaccineScheduleJpaRepository
import com.github.allanfvc.springdata.service.CreateVacancyUseCaseImpl
import com.github.allanfvc.springdata.service.GetVacancyUseCaseImpl
import com.github.allanfvc.springdata.service.ScheduleVaccineUseCaseImpl
import com.github.allanfvc.usecases.CreateVacancyUseCase
import com.github.allanfvc.usecases.GetVacancyUseCase
import com.github.allanfvc.usecases.ScheduleVaccineUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VaccineScheduleProviderConfiguration {

    @Bean
    fun getVacancyUseCase(repository: VaccineScheduleJpaRepository): GetVacancyUseCase {
        return GetVacancyUseCaseImpl(repository)
    }

    @Bean
    fun createVacancyUseCase(repository: VaccineScheduleJpaRepository): CreateVacancyUseCase {
        return CreateVacancyUseCaseImpl(repository)
    }

    @Bean
    fun scheduleVaccineUseCase(repository: VaccineScheduleJpaRepository): ScheduleVaccineUseCase {
        return ScheduleVaccineUseCaseImpl(repository)
    }
}