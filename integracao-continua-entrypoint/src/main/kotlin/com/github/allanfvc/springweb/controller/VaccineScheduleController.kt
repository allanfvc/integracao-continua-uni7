package com.github.allanfvc.springweb.controller

import com.github.allanfvc.entities.VaccineSchedule
import com.github.allanfvc.springweb.dto.CreateVacancyDTO
import com.github.allanfvc.springweb.dto.ScheduleVaccineDTO
import com.github.allanfvc.usecases.RegisterPatientUseCase
import com.github.allanfvc.usecases.ScheduleVaccineUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(VaccineScheduleController.ACTIVE_PATH)
class VaccineScheduleController(
    private val patientService: RegisterPatientUseCase,
    private val scheduleService: ScheduleVaccineUseCase,
) {
    companion object {
        internal const val ACTIVE_PATH = "/vaccine-schedules"
    }

    @PatchMapping("{id}/schedule")
    fun schedule(@PathVariable("id") id: Int,
                 @RequestBody request: ScheduleVaccineDTO
    ): VaccineSchedule {
        val patient = patientService.getPatientByCpf(request.patientCPF)
            ?: throw Exception("The informed patient was not found.")
        val schedule = scheduleService.getVaccineScheduleById(id)
            ?: throw Exception("The informed schedule was not found.")
        return scheduleService.schedule(patient, schedule)
    }
}