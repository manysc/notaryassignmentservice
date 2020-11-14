package com.savasys.notaryassignmentservice

import com.savasys.notaryassignmentservice.entities.*
import com.savasys.notaryassignmentservice.repositories.EscrowOfficersRepository
import com.savasys.notaryassignmentservice.repositories.NotariesRepository
import com.savasys.notaryassignmentservice.repositories.NotarizationsRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class NotaryAssignmentServiceConfiguration {

//    @Bean
//    fun databaseInitializer(escrowOfficersRepository: EscrowOfficersRepository) = ApplicationRunner {
//        escrowOfficerRepository.save(EscrowOfficer("Manuel", "Salas","6612S Willow Vista Dr. Tucson," +
//                " AZ 85756", "520-409-0295", "manuel.salasc@gmail.com", "SAVASYS"))
//
//        escrowOfficerRepository.save(EscrowOfficer("Manuel", "Cardenas","6612S Willow Vista Dr. Tucson," +
//                " AZ 85756", "520-409-0295", "manuel.salasc@gmail.com", "SAVASYS"))

//    }

//    @Bean
//    fun databaseInitializer(notariesRepository: NotariesRepository) = ApplicationRunner {
//        notariesRepository.save(Notary("Manuel", "Salas","6612S Willow Vista Dr. Tucson," +
//                " AZ 85756", "520-409-0295", "manuel.salasc@gmail.com", "SAVASYS", NotaryCertification(realStateAgent = true, signingAgent = true, attorney = true, loanOfficer = true), Date(), "100 K", 15, 10))
//        notariesRepository.save(Notary("Manuel", "Cardenas","6612S Willow Vista Dr. Tucson," +
//                " AZ 85756", "520-409-0295", "manuel.cardenas@gmail.com", "SAVASYS", NotaryCertification(realStateAgent = false, signingAgent = true, attorney = false, loanOfficer = true), Date(), "50 K", 10, 8))
//    }

    @Bean
    fun databaseInitializer(notarizationsRepository: NotarizationsRepository) = ApplicationRunner {
//        notarizationsRepository.save(Notarization("1234AA6Y", 1, NotarizationCategory.PURCHASE, true, 175F, Date(),
//                NotarizationStatus.ASSIGNED, NotarizationDeliveryMethod.UPS, "This is the best notarization ever made!!!",
//                NotarizationClient("Omar", "Samaniego", "1234E Broadway Rd, Tucson AZ 85745", "520-444-1234",
//                        "omar.samaniego@gmail.com"), 77))
//
//        notarizationsRepository.save(Notarization("9876AA6Y", 1, NotarizationCategory.REFINANCE, false, 160F, Date(),
//                NotarizationStatus.COMPLETED, NotarizationDeliveryMethod.FEDEX, "Notary made it all possible on time!!!",
//                NotarizationClient("Brad", "Pit", "1234E Broadway Rd, New York NY 85745", "754-333-6547",
//                        "brad.pit@gmail.com"), 77))
    }
}