package com.example.readserver

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class Service(
    private val teamMemberRepository: TeamMemberRepository
) {
    fun findTeam(teamId: Long): TeamResponse = runBlocking(Dispatchers.IO) {
        val teamMemberDocument = teamMemberRepository.findByTeamIdOrderByDateTimeDesc(teamId)
            .awaitFirst() ?: throw RuntimeException()

        Gson().fromJson(teamMemberDocument.data, TeamResponse::class.java)
    }

    fun findTeams(): List<TeamResponse> = runBlocking(Dispatchers.IO) {
        teamMemberRepository.findByOrderByDateTimeDesc()
            .collectList().awaitSingle()!!
            .map { Gson().fromJson(it.data, TeamResponse::class.java) }
    }
}