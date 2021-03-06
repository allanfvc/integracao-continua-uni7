package com.github.allanfvc.configuration

import com.github.allanfvc.springweb.controller.HomeController
import com.github.allanfvc.springweb.controller.PatientController
import com.github.allanfvc.springweb.controller.VaccinationSiteController
import com.github.allanfvc.springweb.controller.VaccineScheduleController
import com.github.allanfvc.usecases.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class EndpointConfiguration {

    @Bean
    open fun home(): HomeController {
        return HomeController()
    }

    @Bean
    open fun patients(register: RegisterPatientUseCase, listVaccinationSites: ListVaccinationSitesUseCase): PatientController {
        return PatientController(register, listVaccinationSites)
    }

    @Bean
    open fun vaccinationSites(
        register: RegisterVaccinationSiteUseCase,
        vacancyUseCase: GetVacancyUseCase,
        getVaccinationSite: GetVaccinationSiteUseCase,
        createVacancyUseCase: CreateVacancyUseCase
        ): VaccinationSiteController {
        return VaccinationSiteController(register, vacancyUseCase, getVaccinationSite, createVacancyUseCase)
    }

    @Bean
    open fun vaccineSchedule(
        register: RegisterPatientUseCase,
        scheduleVaccineUseCase: ScheduleVaccineUseCase): VaccineScheduleController {
        return VaccineScheduleController(register, scheduleVaccineUseCase)
    }

}