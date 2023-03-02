package com.seytkalievm.denettesttree.data.model

class Node (
    val parent: Node? = null,
    private val _children: MutableList<Node> = mutableListOf()
) {

    val children get() = _children

    fun addChild() {
        _children.add(Node(parent = this))
    }

    fun deleteChild(node: Node) = _children.remove(node)

    /*
    Take last 20 bytes of the hashCode
     */
    fun getName() = this.hashCode().toString(2).takeLast(20)


}