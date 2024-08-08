package com.example.myapplication.data

import java.util.UUID

object Scenes {
    private val scenes = mutableListOf<Scene>()

    fun init(list: List<Scene>) {
        for (item in list)
            scenes.add(item)
    }

    fun add(scene: Scene) {
        scenes.add(scene)
    }

    fun edit(scene: Scene) {
        scenes[scenes.indexOfFirst { it.id == scene.id }] = scene
    }

    fun remove(scene: Scene) {
        scenes.removeIf { it == scene }
    }

    fun clear() {
        scenes.clear()
    }

    fun findById(id: UUID): Scene? {
        return scenes.find { it.id == id }
    }
    fun findByName(name: String): List<Scene> {
        val searchedNotes = mutableListOf<Scene>()
        for (item in scenes) {
            if (item.name == name)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByDescription(description: String): List<Scene> {
        val searchedNotes = mutableListOf<Scene>()
        for (item in scenes) {
            if (item.description == description)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByCharacters(characters: String): List<Scene> {
        val searchedNotes = mutableListOf<Scene>()
        for (item in scenes) {
            if (item.characters == characters)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByLocation(location: String): List<Scene> {
        val searchedNotes = mutableListOf<Scene>()
        for (item in scenes) {
            if (item.location == location)
                searchedNotes.add(item)
        }
        return searchedNotes
    }

    fun getSortedNew() : List<Scene> = scenes.sortedByDescending { it.date }
    fun getSortedOld() : List<Scene> = scenes.sortedBy { it.date }
    fun getSortedAZ() : List<Scene> = scenes.sortedBy { it.name }
    fun getSortedZA() : List<Scene> = scenes.sortedByDescending { it.name }

    fun getNamesList(sortedScenes: List<Scene>): List<String> {
        val scenesNames = mutableListOf<String>()
        for (item in sortedScenes.indices) {
            scenesNames.add(sortedScenes[item].name)
        }
        return scenesNames
    }
}