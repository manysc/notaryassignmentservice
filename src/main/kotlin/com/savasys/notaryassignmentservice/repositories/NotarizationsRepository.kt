package com.savasys.notaryassignmentservice.repositories

import com.savasys.notaryassignmentservice.entities.Notarization
import com.savasys.notaryassignmentservice.entities.NotarizationStatus
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NotarizationsRepository : CrudRepository<Notarization, Long> {

    fun findByStatus(status: NotarizationStatus): MutableList<Notarization>

    fun findByEscrowNumber(escrowNumber: String): Notarization?

    fun existsByEscrowNumber(escrowNumber: String): Boolean

}