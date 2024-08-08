package com.example.myapplication.viewmodels

import android.app.Application
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.R
import com.example.myapplication.data.Characters
import com.example.myapplication.data.Ideas
import com.example.myapplication.data.Locations
import com.example.myapplication.data.Scenes
import com.example.myapplication.data.UtilObject

class NotesFragmentVM(
    private val application: Application
) : AndroidViewModel(application) {
    private val ideas = Ideas
    private val scenes = Scenes
    private val characters = Characters
    private val locations = Locations
    private val utilObject = UtilObject

    private lateinit var namesList: List<String>
    private var curSort: Int = 0

    fun getSpinnerAdapter(): ArrayAdapter<String> {
        val variants = listOf(
            application.getString(R.string.sort_new),
            application.getString(R.string.sort_old),
            application.getString(R.string.sort_az),
            application.getString(R.string.sort_za))
        val arrayAdapter = ArrayAdapter(application, android.R.layout.simple_spinner_item, variants)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return arrayAdapter
    }

    fun getListAdapter(): ArrayAdapter<String> {
        setNamesListSortedNew()
        return ArrayAdapter(application, android.R.layout.simple_list_item_1, namesList)
    }

    fun spinnerItemSelected(position: Int): ArrayAdapter<String> {
        when (position) {
            1 -> {
                curSort = 1
                setNamesListSortedOld()
            }
            2 -> {
                curSort = 2
                setTypeNamesListSortedAZ()
            }
            3 -> {
                curSort = 3
                setTypeNamesListSortedZA()
            }
            else -> {
                curSort = 0
                setNamesListSortedNew()
            }
        }
        return ArrayAdapter(application, android.R.layout.simple_list_item_1, namesList)
    }

    fun listItemSelected(position: Int) {
        when (utilObject.getCurNoteType()) {
            "ideas" -> {
                val id = when (curSort) {
                    1 -> ideas.getSortedOld()[position].id
                    2 -> ideas.getSortedAZ()[position].id
                    3 -> ideas.getSortedZA()[position].id
                    else -> ideas.getSortedNew()[position].id
                }
                utilObject.setCurItemId(id)
            }
            "scenes" -> {
                val id = when (curSort) {
                    1 -> scenes.getSortedOld()[position].id
                    2 -> scenes.getSortedAZ()[position].id
                    3 -> scenes.getSortedZA()[position].id
                    else -> scenes.getSortedNew()[position].id
                }
                utilObject.setCurItemId(id)
            }
            "characters" -> {
                val id = when (curSort) {
                    1 -> characters.getSortedOld()[position].id
                    2 -> characters.getSortedAZ()[position].id
                    3 -> characters.getSortedZA()[position].id
                    else -> characters.getSortedNew()[position].id
                }
                utilObject.setCurItemId(id)
            }
            "locations" -> {
                val id = when (curSort) {
                    1 -> locations.getSortedOld()[position].id
                    2 -> locations.getSortedAZ()[position].id
                    3 -> locations.getSortedZA()[position].id
                    else -> locations.getSortedNew()[position].id
                }
                utilObject.setCurItemId(id)
            }
        }
    }


    private fun setNamesListSortedNew() {
        namesList = when (utilObject.getCurNoteType()) {
            "ideas" -> ideas.getNamesList(ideas.getSortedNew())
            "scenes" -> scenes.getNamesList(scenes.getSortedNew())
            "characters" -> characters.getNamesList(characters.getSortedNew())
            "locations" -> locations.getNamesList(locations.getSortedNew())
            else -> emptyList()
        }
    }

    private fun setNamesListSortedOld() {
        namesList = when (utilObject.getCurNoteType()) {
            "ideas" ->  ideas.getNamesList(ideas.getSortedOld())
            "scenes" -> scenes.getNamesList(scenes.getSortedOld())
            "characters" -> characters.getNamesList(characters.getSortedOld())
            "locations" -> locations.getNamesList(locations.getSortedOld())
            else -> emptyList()
        }
    }

    private fun setTypeNamesListSortedAZ() {
        namesList = when (utilObject.getCurNoteType()) {
            "ideas" -> ideas.getNamesList(ideas.getSortedAZ())
            "scenes" -> scenes.getNamesList(scenes.getSortedAZ())
            "characters" -> characters.getNamesList(characters.getSortedAZ())
            "locations" -> locations.getNamesList(locations.getSortedAZ())
            else -> emptyList()
        }
    }

    private fun setTypeNamesListSortedZA() {
        namesList = when (utilObject.getCurNoteType()) {
            "ideas" -> ideas.getNamesList(ideas.getSortedZA())
            "scenes" -> scenes.getNamesList(scenes.getSortedZA())
            "characters" -> characters.getNamesList(characters.getSortedZA())
            "locations" -> locations.getNamesList(locations.getSortedZA())
            else -> emptyList()
        }
    }
}