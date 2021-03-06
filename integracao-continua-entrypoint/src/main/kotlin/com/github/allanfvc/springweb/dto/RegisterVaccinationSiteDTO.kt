package com.github.allanfvc.springweb.dto

class RegisterVaccinationSiteDTO(
    val name: String,
    val addressName: String,
    val addressDistrict: String,
    val addressCity: String,
    val addressState: String,
    val addressZipCode: String,
    var id: Int?,
) {
    fun fullAddress(): String {
        return "$addressZipCode, $addressName, $addressCity,$addressState"
    }
}
