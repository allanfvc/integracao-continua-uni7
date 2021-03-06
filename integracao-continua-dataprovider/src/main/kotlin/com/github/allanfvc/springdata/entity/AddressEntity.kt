package com.github.allanfvc.springdata.entity

import com.github.allanfvc.entities.Address
import com.github.allanfvc.entities.Location
import com.github.allanfvc.enums.Uf
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity(name = "address")
class AddressEntity(
    @Id
    @Column(name = "id")
    @GenericGenerator(name="address" , strategy="increment")
    @GeneratedValue(generator="address")
    var id: Int? = -1,
    @Column(name = "name")
    val name: String,
    @Column(name = "district")
    val district: String,
    @Column(name = "city")
    val city: String,
    @Column(name = "uf")
    val state: String,
    @Column(name = "zipCode")
    val zipCode: String,
    @Column(name = "latitude")
    val latitude: Double,
    @Column(name = "longitude")
    val longitude: Double
) {
    constructor() : this(-1, "", "", "", "","", Double.MIN_VALUE, Double.MIN_VALUE)
    constructor(model: Address): this(
        model.id,
        model.name,
        model.district,
        model.city,
        model.state.abbreviation,
        model.zipCode,
        model.location.lat,
        model.location.lng)

    fun toModel() = Address(
        name,
        district,
        city,
        Uf.get(state)!!,
        zipCode,
        id,
        Location(latitude,longitude)
    )
}