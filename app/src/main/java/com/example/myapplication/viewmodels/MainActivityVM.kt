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

class MainActivityVM(
    private val dao: NotesDao,
    application: Application
) : AndroidViewModel(application) {
    private val ideas = Ideas
    private val scenes = Scenes
    private val characters = Characters
    private val locations = Locations

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)


    init {
        ideas.clear()
        scenes.clear()
        characters.clear()
        locations.clear()

        //initSome()

        initializeNotes()
    }

    private fun initializeNotes() {
        uiScope.launch {
            getNotesFromDatabase()
        }
    }

    private suspend fun getNotesFromDatabase() {
        withContext(Dispatchers.IO) {
            ideas.init(dao.getAllIdeas())
            scenes.init(dao.getAllScenes())
            characters.init(dao.getAllCharacters())
            locations.init(dao.getAllLocations())
        }
    }

    private suspend fun initSome() {
        withContext(Dispatchers.IO) {
            val idea1 = Idea(UUID.randomUUID(), System.currentTimeMillis(), "Idea A", "Descr1")
            val idea2 = Idea(UUID.randomUUID(), System.currentTimeMillis(), "Idea B", "Descr2")
            val idea3 = Idea(UUID.randomUUID(), System.currentTimeMillis(), "Idea C", "Descr3")

            val scene1 = Scene(UUID.randomUUID(), System.currentTimeMillis(), "Scene A", "Descr1", "Person1", "Location1")
            val scene2 = Scene(UUID.randomUUID(), System.currentTimeMillis(), "Scene B", "Descr2", "Person2", "Location2")
            val scene3 = Scene(UUID.randomUUID(), System.currentTimeMillis(), "Scene C", "Descr3", "Person3", "Location3")

            val character1 = Character(UUID.randomUUID(), System.currentTimeMillis(), "Char A", "20","Descr1", "History1", "Role1", "Scene1")
            val character2 = Character(UUID.randomUUID(), System.currentTimeMillis(), "Char B", "22", "Descr2", "History2", "Role2", "Scene2")
            val character3 = Character(UUID.randomUUID(), System.currentTimeMillis(), "Char C", "32", "Descr3", "History3", "Role3", "Scene3")

            val location1 = Location(UUID.randomUUID(), System.currentTimeMillis(), "Loc A", "Descr1", "Scene1")
            val location2 = Location(UUID.randomUUID(), System.currentTimeMillis(), "Loc B", "Descr2", "Scene2")
            val location3 = Location(UUID.randomUUID(), System.currentTimeMillis(), "Loc C", "Descr3", "Scene3")

            dao.insertIdea(idea1)
            dao.insertIdea(idea2)
            dao.insertIdea(idea3)

            dao.insertScene(scene1)
            dao.insertScene(scene2)
            dao.insertScene(scene3)

            dao.insertCharacter(character1)
            dao.insertCharacter(character2)
            dao.insertCharacter(character3)

            dao.insertLocation(location1)
            dao.insertLocation(location2)
            dao.insertLocation(location3)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}