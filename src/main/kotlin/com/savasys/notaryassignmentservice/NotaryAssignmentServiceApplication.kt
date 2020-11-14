package com.savasys.notaryassignmentservice

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
@ComponentScan(basePackages = ["com.savasys.notaryassignmentservice"])
class NotaryAssignmentServiceApplication

fun main(args: Array<String>) {
	runApplication<NotaryAssignmentServiceApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}