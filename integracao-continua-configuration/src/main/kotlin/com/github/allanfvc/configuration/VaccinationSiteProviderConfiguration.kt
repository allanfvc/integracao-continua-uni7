package com.github.allanfvc.configuration

import com.github.allanfvc.springdata.repository.AddressJpaRepository
import com.github.allanfvc.springdata.repository.VaccinationSiteJpaRepository
import com.github.allanfvc.springdata.service.GetVaccinationSiteUseCaseImpl
import com.github.allanfvc.springdata.service.ListVaccinationSitesUseCaseImpl
import com.github.allanfvc.springdata.service.RegisterVaccinationSiteUseCaseImpl
import com.github.allanfvc.usecases.GetVaccinationSiteUseCase
import com.github.allanfvc.usecases.ListVaccinationSitesUseCase
import com.github.allanfvc.usecases.RegisterVaccinationSiteUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class VaccinationSiteProviderConfiguration {
    @Bean
    @Primary
    fun loadRegisterVaccinationSite(
        repository: VaccinationSiteJpaRepository,
        addressJpaRepository: AddressJpaRepository): RegisterVaccinationSiteUseCase {
        return RegisterVaccinationSiteUseCaseImpl(repository, addressJpaRepository)
    }

    @Bean
    fun loadListVaccinationSite(
        repository: VaccinationSiteJpaRepository): ListVaccinationSitesUseCase {
        return ListVaccinationSitesUseCaseImpl(repository)
    }

    @Bean
    fun getVaccinationSite(repository: VaccinationSiteJpaRepository): GetVaccinationSiteUseCase {
        return GetVaccinationSiteUseCaseImpl(repository)
    }

}