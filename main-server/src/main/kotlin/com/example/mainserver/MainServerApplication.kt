package com.example.mainserver

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class MainServerApplication

fun main(args: Array<String>) {
    runApplication<MainServerApplication>(*args)
}


@Component
class InitService(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository
) {

    @PostConstruct
    fun init() {
        val team1 = teamRepository.save(Team(name = "team1", description = "this is team 1"))
        val team2 = teamRepository.save(Team(name = "team2", description = "this is team 2"))

        memberRepository.saveAll(
            listOf(
                Member(name = "member1", age = 10, team = team1),
                Member(name = "member2", age = 11, team = team1)
            )
        )
        memberRepository.saveAll(
            listOf(
                Member(name = "member3", age = 20, team = team2),
                Member(name = "member4", age = 21, team = team2)
            )
        )

    }
}