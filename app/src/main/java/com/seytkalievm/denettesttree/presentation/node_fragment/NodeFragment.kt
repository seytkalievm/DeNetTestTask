package com.seytkalievm.denettesttree.presentation.node_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.denettesttree.databinding.FragmentNodeBinding
import com.seytkalievm.denettesttree.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NodeFragment : Fragment() {

    private val viewModel: NodeViewModel by viewModels()
    private lateinit var binding: FragmentNodeBinding
    private val args: NodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNode(args.node)

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
                (activity as MainActivity)
                    .supportActionBar?.setDisplayHomeAsUpEnabled(node.parent != "null")
            }
            children.observe(viewLifecycleOwner) { children -> adapter.submitList(children) }
        }

    }

    private fun navigateToChild(child: String) {
        val action = NodeFragmentDirections.actionNodeFragmentSelf()
        action.node = child
        findNavController().navigate(action)
    }

    private fun deleteChild(child: String) {
        viewModel.deleteChild(child)
    }

}