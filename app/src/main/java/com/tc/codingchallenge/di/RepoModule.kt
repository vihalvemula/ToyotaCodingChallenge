package com.tc.codingchallenge.di

import com.tc.codingchallenge.data.repoImpl.QuotesRepoImpl
import com.tc.codingchallenge.domain.repo.QuotesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepoModule {
    @Binds
    @Singleton
    abstract fun bindRepo(quotesRepoImpl: QuotesRepoImpl):QuotesRepo
}