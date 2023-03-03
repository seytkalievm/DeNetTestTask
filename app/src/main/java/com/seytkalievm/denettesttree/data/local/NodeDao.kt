package com.seytkalievm.denettesttree.data.local

import androidx.room.*
import com.seytkalievm.denettesttree.data.model.Node
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {

    @Update
    suspend fun updateNode(node: Node)

    @Insert
    suspend fun insert(node: Node)

    @Query("SELECT * FROM nodes_table WHERE id LIKE :id")
    fun getNodeById(id: String): Flow<Node>

    @Query("SELECT * FROM nodes_table WHERE id LIKE :id")
    suspend fun getChildById(id: String): Node

    @Query("SELECT * FROM nodes_table WHERE parent LIKE 'null'")
    fun getRoot(): Flow<Node>

    @Query("DELETE FROM nodes_table WHERE id LIKE :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM nodes_table WHERE parent LIKE :id")
    fun getNodeChildren(id: String): Flow<List<Node>>

    @Delete
    suspend fun deleteNode(node: Node)

}