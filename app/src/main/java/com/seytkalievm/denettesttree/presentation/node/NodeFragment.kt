package com.seytkalievm.denettesttree.presentation.node


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        viewModel.node.observe(viewLifecycleOwner) {
            binding.textView.text = it.getName()
        }

        val adapter = NodeChildAdapter {
            Log.i("NodeFragment", "onViewCreated: clicked")
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.children.observe(viewLifecycleOwner) { children ->
            Log.i("NodeFragment", "addChild: ${children.joinToString(" "){it.getName()}}")
            adapter.submitList(children)
            adapter.notifyItemInserted(children.size)
        }

        binding.floatingActionButton.setOnClickListener {
            viewModel.addChild()
        }

    }


}