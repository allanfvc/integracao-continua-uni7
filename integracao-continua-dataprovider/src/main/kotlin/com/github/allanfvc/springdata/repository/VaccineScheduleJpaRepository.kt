package com.github.allanfvc.springdata.repository

import com.github.allanfvc.springdata.entity.PatientEntity
import com.github.allanfvc.springdata.entity.VaccineScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface VaccineScheduleJpaRepository: JpaRepository<VaccineScheduleEntity, Int> {
    @Query(value =
        "SELECT u FROM VaccineScheduleEntity u WHERE u.vaccinationSite.id = ?1 and u.dateTime between ?2 and ?3")
    fun findByVaccinationSiteIdAndDate(vaccinationSiteId: Int, start: LocalDateTime, end: LocalDateTime): List<VaccineScheduleEntity>

//    @Modifying
//    @Query("update VaccineScheduleEntity u set u.patient = :patient where u.id = :id")
//    fun schedule(@Param("id") id: Int, @Param("patient") patient: PatientEntity): Int
}