package com.seytkalievm.denettesttree.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Node (
    private val _parent: Node? = null,
    private val _children: MutableList<Node> = mutableListOf()
) : Parcelable {
    val children get() = _children
    val parent get() = _parent

    fun addChild() = _children.add(Node(_parent = this))

    fun deleteChild(node: Node) = _children.remove(node)

    /*
    Take last 20 bytes of the hashCode
     */
    fun getName() = this.hashCode().toString(2).takeLast(20)

}