package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNotesSearchBinding
import com.example.myapplication.viewmodels.NotesSearchFragmentVM
import com.example.myapplication.viewmodels.NotesSearchFragmentVMF

class NotesSearchFragment : Fragment() {
    private lateinit var viewModel: NotesSearchFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentNotesSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_notes_search, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = NotesSearchFragmentVMF(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[NotesSearchFragmentVM::class.java]

        binding.searchSpinner.adapter = viewModel.getSpinnerAdapter()

        binding.searchSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.spinnerItemSelected(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.searchBtn.setOnClickListener {
            binding.searchedList.adapter = viewModel.getSearchedListAdapter(binding.searchField.text.toString())
            binding.searchedList.setOnItemClickListener { _, _, position, _ ->
                viewModel.listItemSelected(position)
                findNavController().navigate(R.id.noteAddShowFragment)
            }
        }

        return binding.root
    }
}