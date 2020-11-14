package com.savasys.notaryassignmentservice.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class EscrowOfficer(
        var name: String,
        var lastName: String,
        var address: String,
        var phoneNumber: String,
        var email: String,
        var company: String,
        @Id @GeneratedValue var id: Long? = null)