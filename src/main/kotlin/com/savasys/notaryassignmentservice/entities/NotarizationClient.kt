package com.savasys.notaryassignmentservice.entities

data class NotarizationClient(
        var name: String,
        var lastName: String,
        var address: String,
        var phoneNumber: String,
        var email: String
) {
    constructor() : this("", "", "", "", "")
}