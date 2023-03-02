package com.seytkalievm.denettesttree.presentation.node


import android.os.Build
import android.os.Build.VERSION
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
import com.seytkalievm.denettesttree.data.model.Node
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
        val node = if (VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            this.arguments?.getParcelable("node", Node::class.java)
        } else {
            @Suppress("DEPRECATION") this.arguments?.getParcelable("node")
        }

        viewModel.setNode(node)

        viewModel.node.observe(viewLifecycleOwner) {
            binding.textView.text = it.getName()
        }

        val adapter = NodeChildAdapter {
            navigateToChild(it)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.children.observe(viewLifecycleOwner) { children ->
            adapter.submitList(children)
            adapter.notifyItemInserted(children.size)
        }

        binding.floatingActionButton.setOnClickListener {
            viewModel.addChild()
        }

    }

    private fun navigateToChild(child: Node) {
        val action = R.id.action_global_nodeFragment
        val bundle = bundleOf("node" to child)
        findNavController().navigate(action, bundle)
    }


}