package com.savasys.notaryassignmentservice.controllers

import com.savasys.notaryassignmentservice.entities.Notary
import com.savasys.notaryassignmentservice.repositories.NotariesRepository
import org.hibernate.NonUniqueResultException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("/api/notaries")
class NotariesController(private val repository: NotariesRepository) {

    @CrossOrigin
    @GetMapping("/")
    fun getNotaries(): MutableIterable<Notary> = repository.findAll()

    @CrossOrigin
    @GetMapping("/{id}")
    fun getNotary(@PathVariable id: Long): Notary {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Notary doesn't exist.")
        }

        return repository.findById(id).get()
    }

    @CrossOrigin
    @GetMapping("/{name}/{lastName}")
    fun getNotary(@PathVariable name: String, @PathVariable lastName: String): Notary {
        return repository.findByNameAndLastName(name, lastName)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Notary doesn't exist.")
    }

    @CrossOrigin
    @PostMapping("/")
    fun postNotary(@RequestBody notary: Notary): ResponseEntity<String> {
        if (!repository.existsByNameAndLastName(notary.name, notary.lastName)) {
            repository.save(notary)
            return ResponseEntity("Notary added successfully.", HttpStatus.CREATED)
        }

        throw ResponseStatusException(HttpStatus.NOT_MODIFIED, "Notary already exists.")
    }

    @CrossOrigin
    @PutMapping("/{id}")
    fun putNotary(@PathVariable id: Long, @RequestBody notary: Notary): ResponseEntity<String> {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Notary doesn't exist.")
        }

        val currentNotary = repository.findById(id).get()
        currentNotary.name = notary.name
        currentNotary.lastName = notary.lastName
        currentNotary.address = notary.address
        currentNotary.phoneNumber = notary.phoneNumber
        currentNotary.email = notary.email
        currentNotary.company = notary.company
        currentNotary.certification = notary.certification
        currentNotary.licenseExpirationDate = notary.licenseExpirationDate
        currentNotary.errorOmissionInsuranceAmount = notary.errorOmissionInsuranceAmount
        currentNotary.experienceYears = notary.experienceYears
        currentNotary.score = notary.score

        repository.save(currentNotary)

        return ResponseEntity("Notary updated successfully.", HttpStatus.OK)
    }

    @CrossOrigin
    @DeleteMapping("/{name}/{lastName}")
    fun deleteNotary(@PathVariable name: String, @PathVariable lastName: String): ResponseEntity<String> {
        try {
            val notary = repository.findByNameAndLastName(name, lastName)

            when {
                notary != null -> repository.deleteById(notary.id!!)
                else -> throw ResponseStatusException(HttpStatus.NOT_FOUND, "Notary doesn't exist.")
            }

            return ResponseEntity("Notary deleted successfully.", HttpStatus.OK)
        } catch (e: NonUniqueResultException) {
            throw ResponseStatusException(HttpStatus.MULTIPLE_CHOICES, "There is more than one Notary with the same name.")
        }
    }
}