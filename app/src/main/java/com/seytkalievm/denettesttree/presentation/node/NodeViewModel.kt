package com.seytkalievm.denettesttree.presentation.node

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

    init {
        _node.value = repo.getRoot()
    }
}