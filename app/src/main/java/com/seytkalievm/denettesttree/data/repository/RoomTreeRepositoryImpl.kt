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
        // somehow room thinks that empty list like this [] is of size 1 and is not empty
        if (node.children.size > 1) {
            for (childId in node.children) {
                val child = dao.getChildById(childId)

                // room also may think that non-null node is null
                if (child != null) deleteNode(child)
            }
        }
        dao.deleteNode(node)
    }

    override suspend fun deleteChild(node: Node, childId: String) {
        val newNode = node.copy(children = node.children - childId)
        val child = dao.getChildById(childId)
        deleteNode(child)
        dao.updateNode(newNode)
    }

    override fun getNodeChildren(id: String): Flow<List<Node>> {
        return dao.getNodeChildren(id)
    }

}