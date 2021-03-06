package com.github.allanfvc.usecases

import com.github.allanfvc.entities.Patient
import com.github.allanfvc.entities.VaccineSchedule

interface ScheduleVaccineUseCase {
    fun schedule(patient: Patient, site: VaccineSchedule): VaccineSchedule

    fun getVaccineScheduleById(id: Int): VaccineSchedule?
}