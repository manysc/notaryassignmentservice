package com.savasys.notaryassignmentservice.repositories

import com.savasys.notaryassignmentservice.entities.Notary
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NotariesRepository : CrudRepository<Notary, Long> {

    fun findByNameAndLastName(name: String, lastName: String): Notary?

    fun existsByNameAndLastName(name: String, lastName: String): Boolean
}