package com.seytkalievm.denettesttree.data.repository

import com.seytkalievm.denettesttree.data.model.Node

interface TreeRepository {

    fun getChildren(node: Node): List<Node>

    fun addChild(node: Node)

    fun deleteChild(node: Node, child: Node)

    fun getRoot(): Node

    fun getParent(node: Node): Node?
}