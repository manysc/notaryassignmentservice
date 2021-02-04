package com.savasys.notaryassignmentservice.controllers

import com.savasys.notaryassignmentservice.entities.Notarization
import com.savasys.notaryassignmentservice.entities.NotarizationStatus
import com.savasys.notaryassignmentservice.repositories.NotarizationsRepository
import org.hibernate.NonUniqueResultException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/notarizations")
class NotarizationsController(private val repository: NotarizationsRepository) {

    @CrossOrigin
    @GetMapping("/")
    fun getNotarizations(@RequestParam(required = false) status: NotarizationStatus?): MutableList<Notarization> {
        if (status != null) {
            return repository.findByStatus(status)
        }

        return repository.findAll().toMutableList()
    }

    @CrossOrigin
    @GetMapping("/active")
    fun getActiveNotarizations(): MutableIterable<Notarization> {
        val activeNotarizations = repository.findByStatus(NotarizationStatus.NOT_SET)
        activeNotarizations.addAll(repository.findByStatus(NotarizationStatus.AVAILABLE))
        activeNotarizations.addAll(repository.findByStatus(NotarizationStatus.ASSIGNED))
        activeNotarizations.addAll(repository.findByStatus(NotarizationStatus.RESCHEDULE))
        activeNotarizations.addAll(repository.findByStatus(NotarizationStatus.IN_PROGRESS))
        activeNotarizations.addAll(repository.findByStatus(NotarizationStatus.SIGNED))
        activeNotarizations.addAll(repository.findByStatus(NotarizationStatus.DELIVERED))
        return activeNotarizations
    }

    @CrossOrigin
    @GetMapping("/inProgress")
    fun getInProgressNotarizations(): MutableIterable<Notarization> {
        val inProgressNotarizations = repository.findByStatus(NotarizationStatus.ASSIGNED)
        inProgressNotarizations.addAll(repository.findByStatus(NotarizationStatus.RESCHEDULE))
        inProgressNotarizations.addAll(repository.findByStatus(NotarizationStatus.IN_PROGRESS))
        inProgressNotarizations.addAll(repository.findByStatus(NotarizationStatus.SIGNED))
        inProgressNotarizations.addAll(repository.findByStatus(NotarizationStatus.DELIVERED))
        return inProgressNotarizations
    }

    @CrossOrigin
    @GetMapping("/inactive")
    fun getInactiveNotarizations(): MutableIterable<Notarization> {
        val inactiveNotarizations = repository.findByStatus(NotarizationStatus.COMPLETED)
        inactiveNotarizations.addAll(repository.findByStatus(NotarizationStatus.CANCELLED))
        inactiveNotarizations.addAll(repository.findByStatus(NotarizationStatus.REJECTED))
        return inactiveNotarizations
    }

    @CrossOrigin
    @GetMapping("/{escrowNumber}")
    fun getEscrowOfficer(@PathVariable escrowNumber: String): Notarization {
        return repository.findByEscrowNumber(escrowNumber)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Notarization doesn't exist.")
    }

    @CrossOrigin
    @PostMapping("/")
    fun postNotarization(@RequestBody notarization: Notarization): ResponseEntity<String> {
        if (!repository.existsByEscrowNumber(notarization.escrowNumber)) {
            repository.save(notarization)
            return ResponseEntity("Notarization added successfully.", HttpStatus.CREATED)
        }

        throw ResponseStatusException(HttpStatus.NOT_MODIFIED, "Notarization already exists.")
    }

    @CrossOrigin
    @PutMapping("/{escrowNumber}")
    fun putEscrowOfficer(@PathVariable escrowNumber: String, @RequestBody notarization: Notarization): ResponseEntity<String> {
        if (!repository.existsByEscrowNumber(escrowNumber)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Notarization doesn't exist.")
        }

        val currentNotarization = repository.findByEscrowNumber(escrowNumber)
        currentNotarization?.escrowNumber = notarization.escrowNumber
        currentNotarization?.escrowOfficerId = notarization.escrowOfficerId
        currentNotarization?.category = notarization.category
        currentNotarization?.powerOfAttorneySigning = notarization.powerOfAttorneySigning
        currentNotarization?.fee = notarization.fee
        currentNotarization?.date = notarization.date
        currentNotarization?.status = notarization.status
        currentNotarization?.deliveryMethod = notarization.deliveryMethod
        currentNotarization?.comments = notarization.comments
        currentNotarization?.client = notarization.client
        currentNotarization?.notaryId = notarization.notaryId

        currentNotarization?.let { repository.save(it) }

        return ResponseEntity("Notarization updated successfully.", HttpStatus.OK)
    }

    @CrossOrigin
    @DeleteMapping("/{escrowNumber}")
    fun deleteNotarization(@PathVariable escrowNumber: String): ResponseEntity<String> {
        try {
            val notarization = repository.findByEscrowNumber(escrowNumber)
            when {
                notarization != null -> repository.deleteById(notarization.id!!)
                else -> throw ResponseStatusException(HttpStatus.NOT_FOUND, "Notarization doesn't exist.")
            }

            return ResponseEntity("Notarization deleted successfully.", HttpStatus.OK)
        } catch (e: NonUniqueResultException) {
            throw ResponseStatusException(HttpStatus.MULTIPLE_CHOICES, "There is more than one Notarization with the same escrow number.")
        }
    }
}