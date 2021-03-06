package com.github.allanfvc.springdata.entity

import com.github.allanfvc.entities.Address
import com.github.allanfvc.entities.Patient
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*
import kotlin.jvm.Transient

@Entity(name = "patient")
class PatientEntity (
    @Id
    @Column(name = "cpf")
    var cpf: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "birthday")
    val birthDay: LocalDate,
    @ManyToOne
    @JoinColumn(name="patient_address",referencedColumnName="id")
    var address: AddressEntity
) {
    constructor() : this("", "", LocalDate.now(), AddressEntity())
    constructor(model: Patient): this(model.cpf, model.name, model.birthDay, AddressEntity(model.address))

    fun toModel() = Patient(name, birthDay, address.toModel(), cpf)
}