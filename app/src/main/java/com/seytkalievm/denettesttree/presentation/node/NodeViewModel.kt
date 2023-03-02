package com.seytkalievm.denettesttree.presentation.node

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seytkalievm.denettesttree.data.model.Node
import com.seytkalievm.denettesttree.data.repository.TreeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NodeViewModel @Inject constructor(
    private val repo: TreeRepository
): ViewModel() {
    private val _node = MutableLiveData<Node>()
    val node: LiveData<Node> get() = _node

    private val _children = MutableLiveData<List<Node>>()
    val children: LiveData<List<Node>> get() = _children

    init {
        _node.value = repo.getRoot()
        _children.value = node.value?.children
    }

    fun addChild() {
        node.value?.let {node ->
            repo.addChild(node)
            _children.postValue(node.children)
        }
    }
}