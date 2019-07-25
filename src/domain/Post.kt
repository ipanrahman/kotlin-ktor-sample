package com.github.ipan97.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

/**
 * @author Ipan Taufik Rahman
 */
data class Post(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("title")
    val title: String?,

    @JsonProperty("content")
    val content: String?,

    @JsonProperty("category")
    val category: String?,

    @JsonProperty("slug")
    val slug: String?,

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    val createdAt: DateTime,

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
    val updatedAt: DateTime
)
