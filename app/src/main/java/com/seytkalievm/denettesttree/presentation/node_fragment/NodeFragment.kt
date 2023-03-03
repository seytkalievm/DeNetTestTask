package com.seytkalievm.denettesttree.presentation.node_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.denettesttree.R
import com.seytkalievm.denettesttree.databinding.FragmentNodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NodeFragment : Fragment() {

    private val viewModel: NodeViewModel by viewModels()
    private lateinit var binding: FragmentNodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setNode(this.arguments?.getString("node"))

        val adapter = NodeChildAdapter(
            navigationListener = { navigateToChild(it.id) },
            deleteListener = { deleteChild(it.id) }
        )

        binding.apply {
            nodeChildrenRv.adapter = adapter
            nodeChildrenRv.layoutManager = LinearLayoutManager(requireContext())
            addChildFab.setOnClickListener { viewModel.addChild() }
        }

        viewModel.apply {
            node.observe(viewLifecycleOwner) { node ->
                binding.nodeNameTv.text = node.getName()
                viewModel.getNodeChildren(node.id)
            }
            children.observe(viewLifecycleOwner) { children -> adapter.submitList(children) }
        }

    }

    private fun navigateToChild(child: String) {
        val action = R.id.action_global_nodeFragment
        val bundle = bundleOf("node" to child)
        findNavController().navigate(action, bundle)
    }

    private fun deleteChild(child: String) {
        viewModel.deleteChild(child)
    }

}