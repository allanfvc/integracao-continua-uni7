package com.github.allanfvc.springdata.service

import com.github.allanfvc.entities.Patient
import com.github.allanfvc.entities.VaccineSchedule
import com.github.allanfvc.springdata.entity.PatientEntity
import com.github.allanfvc.springdata.entity.VaccineScheduleEntity
import com.github.allanfvc.springdata.repository.VaccineScheduleJpaRepository
import com.github.allanfvc.usecases.ScheduleVaccineUseCase
import javax.transaction.Transactional

class ScheduleVaccineUseCaseImpl(
    private val repository: VaccineScheduleJpaRepository): ScheduleVaccineUseCase {

    override fun schedule(patient: Patient, site: VaccineSchedule): VaccineSchedule {
        site.patient = patient
        return repository.save(VaccineScheduleEntity(site)).toModel()
    }

    override fun getVaccineScheduleById(id: Int): VaccineSchedule? {
        val entity =  repository.findById(id).orElse(null)
        if (entity != null) return entity.toModel()
        return null
    }
}