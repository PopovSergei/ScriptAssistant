package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.data.Character
import com.example.myapplication.data.Characters
import com.example.myapplication.data.Idea
import com.example.myapplication.data.Ideas
import com.example.myapplication.data.Location
import com.example.myapplication.data.Locations
import com.example.myapplication.data.Scene
import com.example.myapplication.data.Scenes
import com.example.myapplication.database.NotesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class NoteAddShowFragmentVM(
    private val dao: NotesDao,
    application: Application
) : AndroidViewModel(application) {
    private val ideas = Ideas
    private val scenes = Scenes
    private val characters = Characters
    private val locations = Locations

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun createIdea(name: String, description: String) {
        val idea = Idea(
            UUID.randomUUID(),
            System.currentTimeMillis(),
            name,
            description
        )
        ideas.add(idea)
        uiScope.launch {
            insertIdea(idea)
        }
    }
    fun createScene(name: String, description: String, characters: String, locations: String) {
        val scene = Scene(
            UUID.randomUUID(),
            System.currentTimeMillis(),
            name,
            description,
            characters,
            locations
        )

        scenes.add(scene)
        uiScope.launch {
            insertScene(scene)
        }
    }
    fun createCharacter(name: String, age: String, description: String, history: String, role: String, scenes: String) {
        val character = Character(
            UUID.randomUUID(),
            System.currentTimeMillis(),
            name,
            age,
            description,
            history,
            role,
            scenes
        )
        characters.add(character)
        uiScope.launch {
            insertCharacter(character)
        }
    }
    fun createLocation(name: String, description: String, scenes: String) {
        val location = Location(
            UUID.randomUUID(),
            System.currentTimeMillis(),
            name,
            description,
            scenes
        )
        locations.add(location)
        uiScope.launch {
            insertLocation(location)
        }
    }

    private suspend fun insertIdea(idea: Idea) {
        withContext(Dispatchers.IO) {
            dao.insertIdea(idea)
        }
    }
    private suspend fun insertScene(scene: Scene) {
        withContext(Dispatchers.IO) {
            dao.insertScene(scene)
        }
    }
    private suspend fun insertCharacter(character: Character) {
        withContext(Dispatchers.IO) {
            dao.insertCharacter(character)
        }
    }
    private suspend fun insertLocation(location: Location) {
        withContext(Dispatchers.IO) {
            dao.insertLocation(location)
        }
    }



    fun editIdea(idea: Idea) {
        ideas.edit(idea)
        uiScope.launch {
            updateIdea(idea)
        }
    }
    fun editScene(scene: Scene) {
        scenes.edit(scene)
        uiScope.launch {
            updateScene(scene)
        }
    }
    fun editCharacter(character: Character) {
        characters.edit(character)
        uiScope.launch {
            updateCharacter(character)
        }
    }
    fun editLocation(location: Location) {
        locations.edit(location)
        uiScope.launch {
            updateLocation(location)
        }
    }

    private suspend fun updateIdea(idea: Idea) {
        withContext(Dispatchers.IO) {
            dao.updateIdea(idea)
        }
    }
    private suspend fun updateScene(scene: Scene) {
        withContext(Dispatchers.IO) {
            dao.updateScene(scene)
        }
    }
    private suspend fun updateCharacter(character: Character) {
        withContext(Dispatchers.IO) {
            dao.updateCharacter(character)
        }
    }
    private suspend fun updateLocation(location: Location) {
        withContext(Dispatchers.IO) {
            dao.updateLocation(location)
        }
    }



    fun removeIdea(idea: Idea) {
        ideas.remove(idea)
        uiScope.launch {
            deleteIdea(idea)
        }
    }
    fun removeScene(scene: Scene) {
        scenes.remove(scene)
        uiScope.launch {
            deleteScene(scene)
        }
    }
    fun removeCharacter(character: Character) {
        characters.remove(character)
        uiScope.launch {
            deleteCharacter(character)
        }
    }
    fun removeLocation(location: Location) {
        locations.remove(location)
        uiScope.launch {
            deleteLocation(location)
        }
    }

    private suspend fun deleteIdea(idea: Idea) {
        withContext(Dispatchers.IO) {
            dao.deleteIdea(idea)
        }
    }
    private suspend fun deleteScene(scene: Scene) {
        withContext(Dispatchers.IO) {
            dao.deleteScene(scene)
        }
    }
    private suspend fun deleteCharacter(character: Character) {
        withContext(Dispatchers.IO) {
            dao.deleteCharacter(character)
        }
    }
    private suspend fun deleteLocation(location: Location) {
        withContext(Dispatchers.IO) {
            dao.deleteLocation(location)
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}