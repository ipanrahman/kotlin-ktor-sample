package com.github.ipan97.database.schema

import org.jetbrains.exposed.dao.IntIdTable

/**
 * @author Ipan Taufik Rahman
 */
object Posts : IntIdTable("posts") {
    val title = varchar("title", 255)
    val content = text("content")
    val category = varchar("category", 100)
    val slug = varchar("slug", 50)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
