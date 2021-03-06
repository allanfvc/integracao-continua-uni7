package com.github.allanfvc.entities

import com.github.allanfvc.enums.Uf

data class Address(
    val name: String,
    val district: String,
    val city: String,
    val state: Uf=Uf.CE,
    val zipCode: String,
    val id: Int?=-1,
    var location: Location) {

    fun isValid(): Boolean {
        val cepRegex = Regex("^[0-9]{5}-[0-9]{3}$")
        return !name.isNullOrBlank()
                && cepRegex.matches(zipCode)
                && !district.isNullOrBlank()
                && !city.isNullOrBlank()
    }

    fun fullAddress(): String {
        return "$zipCode, $name, $city,${state.abbreviation}"
    }
}
