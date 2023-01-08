package com.example.readserver

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class KafkaConsumer(
    private val teamMemberRepository: TeamMemberRepository
) {

    @KafkaListener(topics = ["team-member"])
    fun consumeTeamMemberTopic(message: String) = runBlocking(Dispatchers.IO) {
        val teamResponse = Gson().fromJson(message, TeamResponse::class.java)

        val teamMemberDocument = TeamMemberDocument(id = UUID.randomUUID().toString(), teamId = teamResponse.id, dateTime = LocalDateTime.now(), data = message)

        val teamMemberDocumentMono = teamMemberRepository.save(teamMemberDocument)

        println(teamMemberDocumentMono.awaitSingle())
    }
}

data class TeamResponse(
    val id: Long,
    val name: String,
    val description: String,
    val members: List<MemberResponse>
)

data class MemberResponse(
    val id: Long,
    val name: String,
    val age: Int
)