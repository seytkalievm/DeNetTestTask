package com.seytkalievm.denettesttree.presentation.node

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seytkalievm.denettesttree.data.model.Node
import com.seytkalievm.denettesttree.databinding.NodeChildItemViewBinding

typealias OnClick = (node: Node) -> Unit

class NodeChildAdapter(private val onClick: OnClick)
    : ListAdapter<Node, NodeChildAdapter.NodeChildViewHolder>(DiffCallback) {

    inner class NodeChildViewHolder(
        private val binding: NodeChildItemViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Node) {
            binding.childName.text = child.getName()
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
            NodeChildItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NodeChildViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
        holder.bind(item)
    }

}