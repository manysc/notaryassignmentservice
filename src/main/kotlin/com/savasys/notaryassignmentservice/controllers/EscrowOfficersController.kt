package com.savasys.notaryassignmentservice.controllers

import com.savasys.notaryassignmentservice.entities.EscrowOfficer
import com.savasys.notaryassignmentservice.repositories.EscrowOfficersRepository
import org.hibernate.NonUniqueResultException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/escrowOfficers")
class EscrowOfficersController(private val repository: EscrowOfficersRepository) {

    @CrossOrigin
    @GetMapping("/")
    fun getEscrowOfficers(): MutableIterable<EscrowOfficer> = repository.findAll()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getEscrowOfficer(@PathVariable id: Long): EscrowOfficer {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Escrow Officer doesn't exist.")
        }

        return repository.findById(id).get()
    }

    @CrossOrigin
    @GetMapping("/{name}/{lastName}")
    fun getEscrowOfficer(@PathVariable name: String, @PathVariable lastName: String): EscrowOfficer {
        return repository.findByNameAndLastName(name, lastName)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Escrow Officer doesn't exist.")
    }

    @CrossOrigin
    @PostMapping("/")
    fun postEscrowOfficer(@RequestBody escrowOfficer: EscrowOfficer): ResponseEntity<String> {
        if (!repository.existsByNameAndLastName(escrowOfficer.name, escrowOfficer.lastName)) {
            repository.save(escrowOfficer)
            return ResponseEntity("Escrow Officer added successfully.", HttpStatus.CREATED)
        }

        throw ResponseStatusException(HttpStatus.NOT_MODIFIED, "Escrow Officer already exists.")
    }

    @CrossOrigin
    @PutMapping("/{id}")
    fun putEscrowOfficer(@PathVariable id: Long, @RequestBody escrowOfficer: EscrowOfficer): ResponseEntity<String> {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Escrow Officer doesn't exist.")
        }

        val currentEscrowOfficer = repository.findById(id).get()
        currentEscrowOfficer.name = escrowOfficer.name
        currentEscrowOfficer.lastName = escrowOfficer.lastName
        currentEscrowOfficer.address = escrowOfficer.address
        currentEscrowOfficer.phoneNumber = escrowOfficer.phoneNumber
        currentEscrowOfficer.email = escrowOfficer.email
        currentEscrowOfficer.company = escrowOfficer.company

        repository.save(currentEscrowOfficer)

        return ResponseEntity("Escrow Officer updated successfully.", HttpStatus.OK)
    }

    @CrossOrigin
    @DeleteMapping("/{name}/{lastName}")
    fun deleteEscrowOfficer(@PathVariable name: String, @PathVariable lastName: String): ResponseEntity<String> {
        try {
            val escrowOfficer = repository.findByNameAndLastName(name, lastName)
            when {
                escrowOfficer != null -> repository.deleteById(escrowOfficer.id!!)
                else -> throw ResponseStatusException(HttpStatus.NOT_FOUND, "Escrow Officer doesn't exist.")
            }

            return ResponseEntity("Escrow Officer deleted successfully.", HttpStatus.OK)
        } catch (e: NonUniqueResultException) {
            throw ResponseStatusException(HttpStatus.MULTIPLE_CHOICES, "There is more than one Escrow Officer with the same name.")
        }
    }
}