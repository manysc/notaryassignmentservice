package com.savasys.notaryassignmentservice.entities

import java.util.*
import javax.persistence.*

@Entity
class Notarization(
        var escrowNumber: String,
        var escrowOfficerId: Long,

        @Enumerated(EnumType.STRING)
        var category: NotarizationCategory,

        var powerOfAttorneySigning: Boolean,
        var fee: Float,

        @Temporal(TemporalType.TIMESTAMP)
        var date: Date,

        @Enumerated(EnumType.STRING)
        var status: NotarizationStatus,

        @Enumerated(EnumType.STRING)
        var deliveryMethod: NotarizationDeliveryMethod,

        var comments: String,

        @Embedded
        var client: NotarizationClient,

        var notaryId: Long?,

        @Id @GeneratedValue var id: Long? = null
)