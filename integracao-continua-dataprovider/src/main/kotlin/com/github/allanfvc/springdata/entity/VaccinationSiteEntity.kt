package com.github.allanfvc.springdata.entity

import com.github.allanfvc.entities.VaccinationSite
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity(name = "vaccination_site")
class VaccinationSiteEntity(
    @Id
    @Column(name = "id")
    @GenericGenerator(name="vaccination_site" , strategy="increment")
    @GeneratedValue(generator="vaccination_site")
    var id: Int? = -1,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name="vaccination_site_address",referencedColumnName="id")
    var address: AddressEntity
){
    constructor():this(-1, "", AddressEntity())
    constructor(model: VaccinationSite): this(model.id, model.name, AddressEntity(model.address))

    fun toModel() = VaccinationSite(name, address.toModel(),id)
}