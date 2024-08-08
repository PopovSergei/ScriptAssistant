package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.Character
import com.example.myapplication.data.Idea
import com.example.myapplication.data.Location
import com.example.myapplication.data.Scene

@Dao
interface NotesDao {
    @Insert
    fun insertIdea(idea: Idea)
    @Insert
    fun insertScene(scene: Scene)
    @Insert
    fun insertCharacter(character: Character)
    @Insert
    fun insertLocation(location: Location)

    @Update
    fun updateIdea(idea: Idea)
    @Update
    fun updateScene(scene: Scene)
    @Update
    fun updateCharacter(character: Character)
    @Update
    fun updateLocation(location: Location)

    @Delete
    fun deleteIdea(idea: Idea)
    @Delete
    fun deleteScene(scene: Scene)
    @Delete
    fun deleteCharacter(character: Character)
    @Delete
    fun deleteLocation(location: Location)

    @Query("SELECT * FROM ideas")
    fun getAllIdeas(): List<Idea>
    @Query("SELECT * FROM scenes")
    fun getAllScenes(): List<Scene>
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): List<Character>
    @Query("SELECT * FROM locations")
    fun getAllLocations(): List<Location>
}