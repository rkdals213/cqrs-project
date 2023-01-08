package com.example.readserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val service: Service
) {

    @GetMapping("/team/{teamId}")
    fun findTeam(@PathVariable teamId: Long): TeamResponse {
        return service.findTeam(teamId)
    }

    @GetMapping("/team")
    fun findTeams(): List<TeamResponse> {
        return service.findTeams()
    }
}