package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.UtilObject
import com.example.myapplication.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private val utilObject = UtilObject
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        binding.ideasTitleBtn.setOnClickListener{
            utilObject.setCurNoteType("ideas")
            findNavController().navigate(R.id.NotesFragment)
        }
        binding.scenesTitleBtn.setOnClickListener{
            utilObject.setCurNoteType("scenes")
            findNavController().navigate(R.id.NotesFragment)
        }
        binding.charactersTitleBtn.setOnClickListener{
            utilObject.setCurNoteType("characters")
            findNavController().navigate(R.id.NotesFragment)
        }
        binding.locationsTitleBtn.setOnClickListener{
            utilObject.setCurNoteType("locations")
            findNavController().navigate(R.id.NotesFragment)
        }

        return binding.root
    }
}