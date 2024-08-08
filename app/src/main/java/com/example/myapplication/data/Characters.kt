package com.example.myapplication.data

import java.util.UUID

object Characters {
    private val characters = mutableListOf<Character>()

    fun init(list: List<Character>) {
        for (item in list)
            characters.add(item)
    }

    fun add(character: Character) {
        characters.add(character)
    }

    fun edit(character: Character) {
        characters[characters.indexOfFirst { it.id == character.id }] = character
    }

    fun remove(character: Character) {
        characters.removeIf { it == character }
    }

    fun clear() {
        characters.clear()
    }

    fun findById(id: UUID): Character? {
        return characters.find { it.id == id }
    }
    fun findByName(name: String): List<Character> {
        val searchedNotes = mutableListOf<Character>()
        for (item in characters) {
            if (item.name == name)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByAge(age: String): List<Character> {
        val searchedNotes = mutableListOf<Character>()
        for (item in characters) {
            if (item.age == age)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByDescription(description: String): List<Character> {
        val searchedNotes = mutableListOf<Character>()
        for (item in characters) {
            if (item.description == description)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByHistory(history: String): List<Character> {
        val searchedNotes = mutableListOf<Character>()
        for (item in characters) {
            if (item.history == history)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByRole(role: String): List<Character> {
        val searchedNotes = mutableListOf<Character>()
        for (item in characters) {
            if (item.role == role)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByScenes(scenes: String): List<Character> {
        val searchedNotes = mutableListOf<Character>()
        for (item in characters) {
            if (item.scenes == scenes)
                searchedNotes.add(item)
        }
        return searchedNotes
    }

    fun getSortedNew() : List<Character> = characters.sortedByDescending { it.date }
    fun getSortedOld() : List<Character> = characters.sortedBy { it.date }
    fun getSortedAZ() : List<Character> = characters.sortedBy { it.name }
    fun getSortedZA() : List<Character> = characters.sortedByDescending { it.name }

    fun getNamesList(sortedCharacters: List<Character>): List<String> {
        val charactersNames = mutableListOf<String>()
        for (item in sortedCharacters.indices) {
            charactersNames.add(sortedCharacters[item].name)
        }
        return charactersNames
    }
}