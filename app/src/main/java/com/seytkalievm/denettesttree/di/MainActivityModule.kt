package com.seytkalievm.denettesttree.di

import com.seytkalievm.denettesttree.data.repository.TreeRepository
import com.seytkalievm.denettesttree.data.repository.TreeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainActivityModule {

    @Singleton
    @Binds
    abstract fun provideTreeRepository(repoImpl: TreeRepositoryImpl): TreeRepository

}