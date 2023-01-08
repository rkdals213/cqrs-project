package com.example.mainserver

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository: JpaRepository<Team, Long>

interface MemberRepository: JpaRepository<Member, Long>