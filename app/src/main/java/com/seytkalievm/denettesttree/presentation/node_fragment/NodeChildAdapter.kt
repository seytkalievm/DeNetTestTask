package com.seytkalievm.denettesttree.presentation.node_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seytkalievm.denettesttree.data.model.Node
import com.seytkalievm.denettesttree.databinding.NodeChildItemViewBinding

typealias NavigationListener = (node: Node) -> Unit
typealias DeleteListener = (node: Node) -> Unit

class NodeChildAdapter(
    private val navigationListener: NavigationListener,
    private val deleteListener: DeleteListener,
    ) : ListAdapter<Node, NodeChildAdapter.NodeChildViewHolder>(DiffCallback) {

    inner class NodeChildViewHolder(
        private val binding: NodeChildItemViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Node) {
            binding.childName.text = child.getName()
            binding.deleteButton.setOnClickListener { deleteListener(child) }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Node>() {
        override fun areItemsTheSame(oldItem: Node, newItem: Node): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Node, newItem: Node): Boolean {
            return oldItem.getName() == newItem.getName()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeChildViewHolder {
        return NodeChildViewHolder(
            NodeChildItemViewBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NodeChildViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener { navigationListener(item) }
        holder.bind(item)
    }

}