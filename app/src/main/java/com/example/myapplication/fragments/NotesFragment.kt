package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.UtilObject
import com.example.myapplication.databinding.FragmentNotesBinding
import com.example.myapplication.viewmodels.NotesFragmentVM
import com.example.myapplication.viewmodels.NotesFragmentVMF

class NotesFragment : Fragment() {
    private val utilObject = UtilObject

    private lateinit var viewModel: NotesFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentNotesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_notes, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = NotesFragmentVMF(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[NotesFragmentVM::class.java]

        binding.notesSortSpinner.adapter = viewModel.getSpinnerAdapter()

        binding.notesList.adapter = viewModel.getListAdapter()

        binding.notesSortSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.notesList.adapter = viewModel.spinnerItemSelected(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.notesList.setOnItemClickListener { _, _, position, _ ->
            viewModel.listItemSelected(position)
            utilObject.setCurContext("show")
            findNavController().navigate(R.id.noteAddShowFragment)
        }

        binding.addNoteBtn.setOnClickListener{
            utilObject.setCurContext("add")
            findNavController().navigate(R.id.noteAddShowFragment)
        }

        binding.noteSearchBtn.setOnClickListener{ findNavController().navigate(R.id.notesSearchFragment) }

        return binding.root
    }
}