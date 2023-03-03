package com.seytkalievm.denettesttree.data.repository

import com.seytkalievm.denettesttree.data.local.NodeDao
import com.seytkalievm.denettesttree.data.model.Node
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomTreeRepositoryImpl @Inject constructor(
    private val dao: NodeDao
): RoomTreeRepository {
    override fun getNodeById(id: String): Flow<Node> {
        return dao.getNodeById(id)
    }

    override fun getRoot(): Flow<Node> {
        return dao.getRoot()
    }

    override suspend fun createRoot() {
        dao.insert(Node())
    }

    override suspend fun addChild(node: Node) {
        val newNode = Node(parent = node.id)
        val updatedNode = node.copy(children = node.children + newNode.id)
        dao.insert(newNode)
        dao.updateNode(updatedNode)
    }

    override suspend fun deleteNode(node: Node) {
        if (node.children.isEmpty()){
            dao.deleteNode(node)
            return
        }

        for (child in node.children) {
            val childNode = dao.getChildById(child)
            deleteNode(childNode)
        }
    }

    override suspend fun deleteChildById(id: String) {
        deleteNode(dao.getChildById(id))
    }

    override fun getNodeChildren(id: String): Flow<List<Node>> {
        return dao.getNodeChildren(id)
    }

}