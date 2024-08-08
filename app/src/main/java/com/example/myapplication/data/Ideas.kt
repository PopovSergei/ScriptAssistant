package com.example.myapplication.data

import java.util.UUID

object Ideas {
    private val ideas = mutableListOf<Idea>()

    fun init(list: List<Idea>) {
        for (item in list)
            ideas.add(item)
    }

    fun add(idea: Idea) {
        ideas.add(idea)
    }

    fun edit(idea: Idea) {
        ideas[ideas.indexOfFirst { it.id == idea.id }] = idea
    }

    fun remove(idea: Idea) {
        ideas.removeIf { it == idea }
    }

    fun clear() {
        ideas.clear()
    }

    fun findById(id: UUID): Idea? {
        return ideas.find { it.id == id }
    }
    fun findByName(name: String): List<Idea> {
        val searchedNotes = mutableListOf<Idea>()
        for (item in ideas) {
            if (item.name == name)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByDescription(description: String): List<Idea> {
        val searchedNotes = mutableListOf<Idea>()
        for (item in ideas) {
            if (item.description == description)
                searchedNotes.add(item)
        }
        return searchedNotes
    }

    fun getSortedNew() : List<Idea> = ideas.sortedByDescending { it.date }
    fun getSortedOld() : List<Idea> = ideas.sortedBy { it.date }
    fun getSortedAZ() : List<Idea> = ideas.sortedBy { it.name }
    fun getSortedZA() : List<Idea> = ideas.sortedByDescending { it.name }

    fun getNamesList(sortedIdeas: List<Idea>): List<String> {
        val ideasNames = mutableListOf<String>()
        for (item in sortedIdeas.indices) {
            ideasNames.add(sortedIdeas[item].name)
        }
        return ideasNames
    }
}