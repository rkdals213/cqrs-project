package com.example.mainserver

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class Service(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository
) {

    fun updateTeam(updateTeam: UpdateTeam): Long {
        val team = teamRepository.findByIdOrNull(updateTeam.id)!!
            .apply {
                name = updateTeam.name
                description = updateTeam.description
            }

        return team.id
    }

    fun updateMember(updateMember: UpdateMember): Long {
        val member = memberRepository.findByIdOrNull(updateMember.id)!!
            .apply {
                name = updateMember.name
                age = updateMember.age
            }

        return member.team.id
    }
}