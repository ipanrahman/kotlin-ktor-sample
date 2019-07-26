package com.github.ipan97

import com.github.ipan97.repository.PostRepository
import org.koin.dsl.module

/**
 * @author Ipan Taufik Rahman
 */
val koinModule = module(createdAtStart = true) {
    single { PostRepository() }
}
