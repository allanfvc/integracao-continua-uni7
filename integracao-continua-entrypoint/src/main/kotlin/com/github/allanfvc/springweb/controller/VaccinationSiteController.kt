package com.github.allanfvc.springweb.controller

import com.github.allanfvc.entities.Address
import com.github.allanfvc.entities.VaccineSchedule
import com.github.allanfvc.enums.Uf
import com.github.allanfvc.springdata.service.GetVaccinationSiteUseCaseImpl
import com.github.allanfvc.springweb.dto.CreateVacancyDTO
import com.github.allanfvc.springweb.dto.RegisterVaccinationSiteDTO
import com.github.allanfvc.usecases.CreateVacancyUseCase
import com.github.allanfvc.usecases.GetVacancyUseCase
import com.github.allanfvc.usecases.GetVaccinationSiteUseCase
import com.github.allanfvc.usecases.RegisterVaccinationSiteUseCase
import com.github.allanfvc.web.geocode.GoogleMapsGeocode
import org.apache.commons.lang3.time.DateUtils
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@RestController
@RequestMapping(VaccinationSiteController.ACTIVE_PATH)
class VaccinationSiteController(
    private val registerVaccinationSite: RegisterVaccinationSiteUseCase,
    private val vacancyUseCase: GetVacancyUseCase,
    private val getVaccinationSite: GetVaccinationSiteUseCase,
    private val createVacancyUseCase: CreateVacancyUseCase
) {

    companion object {
        internal const val ACTIVE_PATH = "/vaccination-sites"
    }

    @PostMapping
    fun register(@RequestBody request: RegisterVaccinationSiteDTO): RegisterVaccinationSiteDTO {
        val geocode = GoogleMapsGeocode()
        val location = geocode.getLocation(request.fullAddress())
        val uf = Uf[request.addressState]!!
        val address = Address(
            request.addressName,
            request.addressDistrict,
            request.addressCity,
            uf,
            request.addressZipCode,
            -1,
            location
        )
        val vaccinationSite = registerVaccinationSite.register(request.name, address)
        request.id = vaccinationSite.id
        return request
    }

    @GetMapping("{id}/vacancies")
    fun listVaccinationSites(@PathVariable("id") id: Int,
                             @RequestParam(name = "date") dateString: String): List<VaccineSchedule> {
        return vacancyUseCase.getVacancies(id, LocalDate.parse(dateString))
    }

    @PutMapping("{id}/vacancies")
    fun createVacancy(@PathVariable("id") id: Int,
                 @RequestBody request: CreateVacancyDTO): List<VaccineSchedule> {
        val site = getVaccinationSite.getVaccinationSiteById(id)
            ?: throw Exception("The informed vaccination site doesn't exists.")
        return createVacancyUseCase.createVaccinationSiteSchedule(
            site, request.vacancies, request.toLocalDate())

    }
}