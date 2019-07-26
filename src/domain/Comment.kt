package com.github.ipan97.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

/**
 * @author Ipan Taufik Rahman
 */
data class Comment(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("body")
    val body: String,

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    val createdAt: DateTime,

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    val updatedAt: DateTime
)
