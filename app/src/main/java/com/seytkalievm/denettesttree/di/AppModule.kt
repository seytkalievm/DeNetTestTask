package com.seytkalievm.denettesttree.di

import android.content.Context
import com.seytkalievm.denettesttree.data.local.NodeDao
import com.seytkalievm.denettesttree.data.local.TreeDatabase
import com.seytkalievm.denettesttree.data.repository.RoomTreeRepository
import com.seytkalievm.denettesttree.data.repository.RoomTreeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): TreeDatabase {
        return TreeDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDao(database: TreeDatabase): NodeDao {
        return database.nodeDao
    }

    @Provides
    @Singleton
    fun provideRoomTreeRepository(dao: NodeDao): RoomTreeRepository {
        return RoomTreeRepositoryImpl(dao)
    }
}