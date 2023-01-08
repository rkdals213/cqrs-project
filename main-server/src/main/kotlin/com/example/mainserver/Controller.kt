package com.example.mainserver

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val service: Service,
    private val kafkaProducer: KafkaProducer
) {

    @PostMapping("/update/team")
    fun updateTeam(@RequestBody updateTeam: UpdateTeam) {
        val teamId = service.updateTeam(updateTeam)
        updateEvent(teamId)
    }

    @PostMapping("/update/member")
    fun updateMember(@RequestBody updateMember: UpdateMember) {
        val teamId = service.updateMember(updateMember)
        updateEvent(teamId)
    }

    private fun updateEvent(teamId: Long) {
        kafkaProducer.produceUpdateEvent(teamId)
    }
}