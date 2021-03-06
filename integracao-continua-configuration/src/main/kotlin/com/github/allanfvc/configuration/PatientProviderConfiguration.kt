package com.github.allanfvc.configuration

import com.github.allanfvc.springdata.repository.AddressJpaRepository
import com.github.allanfvc.springdata.repository.PatientJpaRepository
import com.github.allanfvc.springdata.service.RegisterPatientUseCaseImpl
import com.github.allanfvc.usecases.RegisterPatientUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class PatientProviderConfiguration {
    @Bean
    @Primary
    fun loadRegisterPatient(
        patientJpaRepository: PatientJpaRepository,
        addressJpaRepository: AddressJpaRepository): RegisterPatientUseCase {
        return RegisterPatientUseCaseImpl(patientJpaRepository, addressJpaRepository)
    }

}