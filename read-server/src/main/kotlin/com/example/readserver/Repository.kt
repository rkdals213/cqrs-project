package com.example.readserver

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository
import reactor.core.publisher.Flux

interface TeamMemberRepository : ReactiveElasticsearchRepository<TeamMemberDocument, String> {
    fun findByTeamIdOrderByDateTimeDesc(teamId: Long): Flux<TeamMemberDocument>

    fun findByOrderByDateTimeDesc(): Flux<TeamMemberDocument>
}