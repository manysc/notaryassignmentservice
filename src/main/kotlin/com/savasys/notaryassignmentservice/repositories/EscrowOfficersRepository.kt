package com.savasys.notaryassignmentservice.repositories

import com.savasys.notaryassignmentservice.entities.EscrowOfficer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EscrowOfficersRepository : CrudRepository<EscrowOfficer, Long> {

    fun findByNameAndLastName(name: String, lastName: String): EscrowOfficer?

    fun existsByNameAndLastName(name: String, lastName: String): Boolean

}