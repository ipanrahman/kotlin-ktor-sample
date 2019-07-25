package com.github.ipan97.repository

import com.github.ipan97.config.DatabaseConfig
import com.github.ipan97.database.schema.Posts
import com.github.ipan97.domain.Post
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @author Ipan Taufik Rahman
 */
class PostRepository {

    init {
        DatabaseConfig.init()
        transaction {
            SchemaUtils.create(Posts)
        }
    }

    fun findAll(): List<Post> = transaction {
        Posts.selectAll().map { toPost(it) }
    }

    fun findById(id: Int): Post? = transaction {
        Posts.select { Posts.id eq id }
            .mapNotNull { toPost(it) }
            .singleOrNull()
    }

    private fun toPost(row: ResultRow) = Post(
        id = row[Posts.id].value,
        title = row[Posts.title],
        content = row[Posts.content],
        category = row[Posts.category],
        slug = row[Posts.slug],
        createdAt = row[Posts.createdAt],
        updatedAt = row[Posts.updatedAt]
    )
}
