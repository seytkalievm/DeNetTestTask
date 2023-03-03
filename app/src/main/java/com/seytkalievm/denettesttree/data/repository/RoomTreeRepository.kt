package com.seytkalievm.denettesttree.data.repository

import com.seytkalievm.denettesttree.data.model.Node
import kotlinx.coroutines.flow.Flow

interface RoomTreeRepository {
    fun getNodeById(id: String): Flow<Node>

    fun getRoot(): Flow<Node>

    suspend fun createRoot()

    suspend fun addChild(node: Node)

    suspend fun deleteNode(node: Node)

    suspend fun deleteChild(node: Node, childId: String)

    fun getNodeChildren(id: String): Flow<List<Node>>
}