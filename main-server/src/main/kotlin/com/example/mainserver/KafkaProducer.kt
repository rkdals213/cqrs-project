package com.example.mainserver

import com.google.gson.Gson
import org.springframework.data.repository.findByIdOrNull
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun produceUpdateEvent(teamId: Long) {
        val team = teamRepository.findByIdOrNull(teamId)!!
        val members = memberRepository.findAll()
            .filter { it.team.id == team.id }

        val teamResponse = TeamResponse(
            team.id,
            team.name,
            team.description,
            members.map {
                MemberResponse(
                    it.id,
                    it.name,
                    it.age
                )
            }
        )

        val teamResponseString = Gson().toJson(teamResponse)
        kafkaTemplate.send("team-member", teamResponseString)
            .thenAccept { println(it.producerRecord) }
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