package com.github.allanfvc.springdata.entity

import com.github.allanfvc.entities.Patient
import com.github.allanfvc.entities.VaccineSchedule
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="vaccine_schedule")
class VaccineScheduleEntity(
    @Id
    @Column(name = "id")
    @GenericGenerator(name="vaccine_schedule" , strategy="increment")
    @GeneratedValue(generator="vaccine_schedule")
    val id: Int,
    @ManyToOne
    @JoinColumn(name="vaccination_site_id",referencedColumnName="id")
    val vaccinationSite: VaccinationSiteEntity,
    @ManyToOne
    @JoinColumn(name="patient_id",referencedColumnName="cpf")
    val patient: PatientEntity?,
    @Column(name = "DAY")
    val dateTime: LocalDateTime,
    @Column(name = "realized")
    val realized: Boolean = false) {

    constructor(): this(-1, VaccinationSiteEntity(), null, LocalDateTime.now())

    constructor(model: VaccineSchedule):
            this(
                model.id,
                VaccinationSiteEntity(model.vaccinationSite),
                model.patient?.let { PatientEntity(it) },
                model.dateTime,
                model.realized
            )

    fun toModel() = VaccineSchedule(id, vaccinationSite.toModel(), patient?.toModel(),dateTime, realized)
}