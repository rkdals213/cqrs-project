package com.example.readserver

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime

@Document(indexName = "team-member")
data class TeamMemberDocument(
    @Id
    val id: String,

    @Field(type = FieldType.Long)
    val teamId: Long,

    @Field(type = FieldType.Text)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val dateTime: LocalDateTime,

    @Field(type = FieldType.Text)
    val data: String
)
