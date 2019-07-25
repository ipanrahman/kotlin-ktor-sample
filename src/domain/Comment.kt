package com.github.ipan97.domain

import org.joda.time.DateTime

/**
 * @author Ipan Taufik Rahman
 */
data class Comment(
    val id: Long,
    val body: String,
    val createdAt: DateTime,
    val updatedAt: DateTime
)
