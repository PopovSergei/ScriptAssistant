package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.Character
import com.example.myapplication.data.Characters
import com.example.myapplication.data.Idea
import com.example.myapplication.data.Ideas
import com.example.myapplication.data.Location
import com.example.myapplication.data.Locations
import com.example.myapplication.data.Scene
import com.example.myapplication.data.Scenes
import com.example.myapplication.data.UtilObject
import com.example.myapplication.database.NotesDB
import com.example.myapplication.databinding.FragmentNoteAddShowBinding
import com.example.myapplication.viewmodels.NoteAddShowFragmentVM
import com.example.myapplication.viewmodels.NoteAddShowFragmentVMF

class NoteAddShowFragment : Fragment() {
    private val utilObject = UtilObject
    private val ideas = Ideas
    private val scenes = Scenes
    private val characters = Characters
    private val locations = Locations

    private lateinit var viewModel: NoteAddShowFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentNoteAddShowBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_note_add_show, container, false)

        val application = requireNotNull(this.activity).application
        val dao = NotesDB.getInstance(application).getNotesDao()
        val viewModelFactory = NoteAddShowFragmentVMF(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteAddShowFragmentVM::class.java]

        when (utilObject.getCurNoteType()) {
            "ideas" -> {
                binding.ageField.visibility = View.GONE
                binding.historyField.visibility = View.GONE
                binding.roleField.visibility = View.GONE
                binding.scenesField.visibility = View.GONE
                binding.charactersField.visibility = View.GONE
                binding.locationsField.visibility = View.GONE
            }
            "scenes" -> {
                binding.ageField.visibility = View.GONE
                binding.historyField.visibility = View.GONE
                binding.roleField.visibility = View.GONE
                binding.scenesField.visibility = View.GONE
            }
            "characters" -> {
                binding.charactersField.visibility = View.GONE
                binding.locationsField.visibility = View.GONE
            }
            "locations" -> {
                binding.ageField.visibility = View.GONE
                binding.historyField.visibility = View.GONE
                binding.roleField.visibility = View.GONE
                binding.charactersField.visibility = View.GONE
                binding.locationsField.visibility = View.GONE
            }
        }

        if (utilObject.getCurContext() == "add") {
            binding.noteSaveBtn.visibility = View.GONE
            binding.noteRemoveBtn.visibility = View.GONE

            when (utilObject.getCurNoteType()) {
                "ideas" -> {
                    binding.createNoteBtn.setOnClickListener {
                        viewModel.createIdea(
                            binding.nameField.text.toString(),
                            binding.descriptionField.text.toString()
                        )
                        findNavController().popBackStack()
                    }
                }
                "scenes" -> {
                    binding.createNoteBtn.setOnClickListener {
                        viewModel.createScene(
                            binding.nameField.text.toString(),
                            binding.descriptionField.text.toString(),
                            binding.charactersField.text.toString(),
                            binding.locationsField.text.toString()
                        )
                        findNavController().popBackStack()
                    }
                }
                "characters" -> {
                    binding.createNoteBtn.setOnClickListener {
                        viewModel.createCharacter(
                            binding.nameField.text.toString(),
                            binding.ageField.text.toString(),
                            binding.descriptionField.text.toString(),
                            binding.historyField.text.toString(),
                            binding.roleField.text.toString(),
                            binding.scenesField.text.toString()
                        )
                        findNavController().popBackStack()
                    }
                }
                "locations" -> {
                    binding.createNoteBtn.setOnClickListener {
                        viewModel.createLocation(
                            binding.nameField.text.toString(),
                            binding.descriptionField.text.toString(),
                            binding.scenesField.text.toString()
                        )
                        findNavController().popBackStack()
                    }
                }
            }
        } else if (utilObject.getCurContext() == "show") {
            binding.createNoteBtn.visibility = View.GONE

            when (utilObject.getCurNoteType()) {
                "ideas" -> {
                    val curItem = ideas.findById(utilObject.getCurItemId())
                    if (curItem != null) {
                        binding.nameField.setText(curItem.name)
                        binding.descriptionField.setText(curItem.description)

                        binding.noteSaveBtn.setOnClickListener {
                            val idea = Idea(
                                curItem.id,
                                curItem.date,
                                binding.nameField.text.toString(),
                                binding.descriptionField.text.toString()
                            )
                            viewModel.editIdea(idea)
                        }
                        binding.noteRemoveBtn.setOnClickListener {
                            viewModel.removeIdea(curItem)
                            findNavController().popBackStack()
                        }
                    }
                }
                "scenes" -> {
                    val curItem = scenes.findById(utilObject.getCurItemId())
                    if (curItem != null) {
                        binding.nameField.setText(curItem.name)
                        binding.descriptionField.setText(curItem.description)
                        binding.charactersField.setText(curItem.characters)
                        binding.locationsField.setText(curItem.location)

                        binding.noteSaveBtn.setOnClickListener {
                            val scene = Scene(
                                curItem.id,
                                curItem.date,
                                binding.nameField.text.toString(),
                                binding.descriptionField.text.toString(),
                                binding.charactersField.text.toString(),
                                binding.locationsField.text.toString()
                            )
                            viewModel.editScene(scene)
                        }
                        binding.noteRemoveBtn.setOnClickListener {
                            viewModel.removeScene(curItem)
                            findNavController().popBackStack()
                        }
                    }
                }
                "characters" -> {
                    val curItem = characters.findById(utilObject.getCurItemId())
                    if (curItem != null) {
                        binding.nameField.setText(curItem.name)
                        binding.ageField.setText(curItem.age)
                        binding.descriptionField.setText(curItem.description)
                        binding.historyField.setText(curItem.history)
                        binding.roleField.setText(curItem.role)
                        binding.scenesField.setText(curItem.scenes)

                        binding.noteSaveBtn.setOnClickListener {
                            val character = Character(
                                curItem.id,
                                curItem.date,
                                binding.nameField.text.toString(),
                                binding.ageField.text.toString(),
                                binding.descriptionField.text.toString(),
                                binding.historyField.text.toString(),
                                binding.roleField.text.toString(),
                                binding.scenesField.text.toString()
                            )
                            viewModel.editCharacter(character)
                        }
                        binding.noteRemoveBtn.setOnClickListener {
                            viewModel.removeCharacter(curItem)
                            findNavController().popBackStack()
                        }
                    }
                }
                "locations" -> {
                    val curItem = locations.findById(utilObject.getCurItemId())
                    if (curItem != null) {
                        binding.nameField.setText(curItem.name)
                        binding.descriptionField.setText(curItem.description)
                        binding.scenesField.setText(curItem.scenes)

                        binding.noteSaveBtn.setOnClickListener {
                            val location = Location(
                                curItem.id,
                                curItem.date,
                                binding.nameField.text.toString(),
                                binding.descriptionField.text.toString(),
                                binding.scenesField.text.toString()
                            )
                            viewModel.editLocation(location)
                        }
                        binding.noteRemoveBtn.setOnClickListener {
                            viewModel.removeLocation(curItem)
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        } else {
            val toast = Toast.makeText(this.requireContext(), getString(R.string.smfWrong), Toast.LENGTH_SHORT)
            toast.show()
        }

        return binding.root
    }
}