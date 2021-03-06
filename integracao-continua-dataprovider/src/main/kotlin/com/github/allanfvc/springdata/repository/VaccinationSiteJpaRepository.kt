package com.github.allanfvc.springdata.repository

import com.github.allanfvc.springdata.entity.VaccinationSiteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface VaccinationSiteJpaRepository: JpaRepository<VaccinationSiteEntity, Int> {

}