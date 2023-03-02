package com.seytkalievm.denettesttree.data.repository

import com.seytkalievm.denettesttree.data.datasource.HardcodedDataSource
import com.seytkalievm.denettesttree.data.model.Node
import javax.inject.Inject

class TreeRepositoryImpl @Inject constructor(): TreeRepository {

    private val dataSource = HardcodedDataSource

    override fun getRoot() = dataSource.tree.root

    override fun getChildren(node: Node): List<Node> {
        return node.children
    }

    override fun addChild(node: Node) {
        node.addChild()
    }

    override fun deleteChild(node: Node, child: Node) {
        node.deleteChild(child)
    }

    override fun getParent(node: Node) = node.parent
}