package com.github.allanfvc.springweb.controller

import com.github.allanfvc.entities.Address
import com.github.allanfvc.enums.Uf
import com.github.allanfvc.springweb.dto.RegisterPatientDTO
import com.github.allanfvc.springweb.dto.VaccinationSiteListDto
import com.github.allanfvc.usecases.ListVaccinationSitesUseCase
import com.github.allanfvc.usecases.RegisterPatientUseCase
import com.github.allanfvc.web.geocode.GoogleMapsGeocode
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(PatientController.ACTIVE_PATH)
class PatientController(
    private val registerPatient: RegisterPatientUseCase,
    private val listVaccinationSites: ListVaccinationSitesUseCase) {
    companion object {
        internal const val ACTIVE_PATH = "/patients"
    }

    @PostMapping
    fun register(@RequestBody request: RegisterPatientDTO): RegisterPatientDTO {
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
        registerPatient.register(
            request.cpf,
            request.name,
            request.convertBirthDayToLocalDate(),
            address
        )
        return request
    }

    @GetMapping("{cpf}/vaccination-sites")
    fun listVaccinationSites(@PathVariable("cpf") cpf: String): List<VaccinationSiteListDto> {
        val patient = registerPatient.getPatientByCpf(cpf)
        when {
            patient != null -> {
                return listVaccinationSites.list(patient)
                    .map { VaccinationSiteListDto(it.id, it.name, it.address.fullAddress()) }
            }
            else -> throw Exception("Could not find patient with cpf $cpf.")
        }
    }

}