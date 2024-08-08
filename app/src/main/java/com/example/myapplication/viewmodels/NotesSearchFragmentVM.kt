package com.example.myapplication.viewmodels

import android.app.Application
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
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
import java.util.UUID

class NotesSearchFragmentVM(
    private val application: Application
) : AndroidViewModel(application) {
    private val ideas = Ideas
    private val scenes = Scenes
    private val characters = Characters
    private val locations = Locations
    private val utilObject = UtilObject

    private var curSearchField: String = "title"

    private lateinit var namesList: List<String>

    private lateinit var searchedIdeas: List<Idea>
    private lateinit var searchedScenes: List<Scene>
    private lateinit var searchedCharacters: List<Character>
    private lateinit var searchedLocations: List<Location>

    fun getSpinnerAdapter(): ArrayAdapter<String> {
        var variants = listOf<String>()
        when (utilObject.getCurNoteType()) {
            "ideas" -> {
                variants = listOf(
                    application.getString(R.string.title),
                    application.getString(R.string.description)
                )
            }
            "scenes" -> {
                variants = listOf(
                    application.getString(R.string.title),
                    application.getString(R.string.description),
                    application.getString(R.string.characters),
                    application.getString(R.string.locations)
                )
            }
            "characters" -> {
                variants = listOf(
                    application.getString(R.string.title),
                    application.getString(R.string.age),
                    application.getString(R.string.description),
                    application.getString(R.string.history),
                    application.getString(R.string.role),
                    application.getString(R.string.scenes)
                )
            }
            "locations" -> {
                variants = listOf(
                    application.getString(R.string.title),
                    application.getString(R.string.description),
                    application.getString(R.string.scenes)
                )
            }
        }
        val arrayAdapter = ArrayAdapter(application, android.R.layout.simple_spinner_item, variants)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return arrayAdapter
    }

    fun spinnerItemSelected(position: Int) {
        when (utilObject.getCurNoteType()) {
            "ideas" -> {
                curSearchField = when (position) {
                    1 -> "description"
                    else -> "title"
                }
            }
            "scenes" -> {
                curSearchField = when (position) {
                    1 -> "description"
                    2 -> "characters"
                    3 -> "location"
                    else -> "title"
                }
            }
            "characters" -> {
                curSearchField = when (position) {
                    1 -> "age"
                    2 -> "description"
                    3 -> "history"
                    4 -> "role"
                    5 -> "scenes"
                    else -> "title"
                }
            }
            "locations" -> {
                curSearchField = when (position) {
                    1 -> "description"
                    2 -> "scenes"
                    else -> "title"
                }
            }
        }
    }

    fun getSearchedListAdapter(searchRequest: String): ArrayAdapter<String> {
        when (utilObject.getCurNoteType()) {
            "ideas" -> {
                searchedIdeas = when (curSearchField) {
                    "description" -> ideas.findByDescription(searchRequest)
                    else -> ideas.findByName(searchRequest)
                }
                setIdeasNamesList(searchedIdeas)
            }
            "scenes" -> {
                searchedScenes = when (curSearchField) {
                    "description" -> scenes.findByDescription(searchRequest)
                    "characters" -> scenes.findByCharacters(searchRequest)
                    "location" -> scenes.findByLocation(searchRequest)
                    else -> scenes.findByName(searchRequest)
                }
                setScenesNamesList(searchedScenes)
            }
            "characters" -> {
                searchedCharacters = when (curSearchField) {
                    "age" -> characters.findByAge(searchRequest)
                    "description" -> characters.findByDescription(searchRequest)
                    "history" -> characters.findByHistory(searchRequest)
                    "role" -> characters.findByRole(searchRequest)
                    "scenes" -> characters.findByScenes(searchRequest)
                    else -> characters.findByName(searchRequest)
                }
                setCharactersNamesList(searchedCharacters)
            }
            "locations" -> {
                searchedLocations = when (curSearchField) {
                    "description" -> locations.findByDescription(searchRequest)
                    "scenes" -> locations.findByScenes(searchRequest)
                    else -> locations.findByName(searchRequest)
                }
                setLocationsNamesList(searchedLocations)
            }
        }
        return ArrayAdapter(application, android.R.layout.simple_list_item_1, namesList)
    }

    private fun setIdeasNamesList(searchedList: List<Idea>) {
        val newNamesList = mutableListOf<String>()
        for (item in searchedList.indices) {
            newNamesList.add(searchedList[item].name)
        }
        namesList = newNamesList
    }
    private fun setScenesNamesList(searchedList: List<Scene>) {
        val newNamesList = mutableListOf<String>()
        for (item in searchedList.indices) {
            newNamesList.add(searchedList[item].name)
        }
        namesList = newNamesList
    }
    private fun setCharactersNamesList(searchedList: List<Character>) {
        val newIdeasNames = mutableListOf<String>()
        for (item in searchedList.indices) {
            newIdeasNames.add(searchedList[item].name)
        }
        namesList = newIdeasNames
    }
    private fun setLocationsNamesList(searchedList: List<Location>) {
        val newIdeasNames = mutableListOf<String>()
        for (item in searchedList.indices) {
            newIdeasNames.add(searchedList[item].name)
        }
        namesList = newIdeasNames
    }

    fun listItemSelected(position: Int) {
        val itemId = when (utilObject.getCurNoteType()) {
            "ideas" -> searchedIdeas[position].id
            "scenes" -> searchedScenes[position].id
            "characters" -> searchedCharacters[position].id
            "locations" -> searchedLocations[position].id
            else -> UUID.randomUUID()
        }
        utilObject.setCurItemId(itemId)
        utilObject.setCurContext("show")
    }
}