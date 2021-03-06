package com.github.allanfvc.springdata.repository

import com.github.allanfvc.springdata.entity.PatientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface PatientJpaRepository: JpaRepository<PatientEntity, Int> {
    fun findByCpf(cpf: String): PatientEntity?
}