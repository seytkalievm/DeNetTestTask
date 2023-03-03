package com.seytkalievm.denettesttree.presentation.node

import androidx.lifecycle.*
import com.seytkalievm.denettesttree.data.model.Node
import com.seytkalievm.denettesttree.data.repository.RoomTreeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodeViewModel @Inject constructor(
    private val repo: RoomTreeRepository,
): ViewModel() {
    private var _node = MutableLiveData<Node>()
    val node: LiveData<Node> get() = _node

    private var _children = MutableLiveData<List<Node>>()
    val children: LiveData<List<Node>> get() = _children

    fun setNode(id: String?) {
        viewModelScope.launch (Dispatchers.IO) {
            if (id == "null" || id == null) {
                repo.getRoot().collect { node ->
                    if (node == null) {
                        repo.createRoot()
                    }
                    _node.postValue(node)
                }
            } else {
                repo.getNodeById(id).collect{
                    _node.postValue(it)
                }
            }

        }
    }

    fun addChild() {
        _node.value?.let { node ->
            viewModelScope.launch (Dispatchers.IO) {
                repo.addChild(node)
            }
        }
    }

    fun getNodeChildren(id: String){
        viewModelScope.launch( Dispatchers.IO) {
            repo.getNodeChildren(id).collect {
                _children.postValue(it)
            }
        }
    }
}