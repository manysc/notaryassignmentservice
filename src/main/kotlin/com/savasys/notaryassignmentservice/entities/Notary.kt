package com.savasys.notaryassignmentservice.entities

import java.util.*
import javax.persistence.*

@Entity
class Notary(
        var name: String,
        var lastName: String,
        var address: String,
        var phoneNumber: String,
        var email: String,
        var company: String,

        @Embedded
        var certification: NotaryCertification,

        @Temporal(TemporalType.TIMESTAMP)
        var licenseExpirationDate: Date,

        var errorOmissionInsuranceAmount: String,
        var experienceYears: Short,
        var score: Short,
        @Id
        @GeneratedValue
        var id: Long? = null
)

@Embeddable
class NotaryCertification(
        var realStateAgent: Boolean = false,
        var signingAgent: Boolean = false,
        var attorney: Boolean = false,
        var loanOfficer: Boolean = false
)
