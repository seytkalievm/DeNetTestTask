package com.seytkalievm.denettesttree.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seytkalievm.denettesttree.data.model.Node

@Database(entities = [Node::class], version = 1, exportSchema = false)
@TypeConverters(ChildConverter::class)
abstract class TreeDatabase: RoomDatabase() {
    abstract val nodeDao: NodeDao

    companion object {
        @Volatile
        private var INSTANCE: TreeDatabase? = null

        fun getInstance(context: Context): TreeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null ) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TreeDatabase::class.java, "tree"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}