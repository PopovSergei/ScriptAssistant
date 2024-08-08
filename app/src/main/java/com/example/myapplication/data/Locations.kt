package com.example.myapplication.data

import java.util.UUID

object Locations {
    private val locations = mutableListOf<Location>()

    fun init(list: List<Location>) {
        for (item in list)
            locations.add(item)
    }

    fun add(location: Location) {
        locations.add(location)
    }

    fun edit(location: Location) {
        locations[locations.indexOfFirst { it.id == location.id }] = location
    }

    fun remove(location: Location) {
        locations.removeIf { it == location }
    }

    fun clear() {
        locations.clear()
    }

    fun findById(id: UUID): Location? {
        return locations.find { it.id == id }
    }
    fun findByName(name: String): List<Location> {
        val searchedNotes = mutableListOf<Location>()
        for (item in locations) {
            if (item.name == name)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByDescription(description: String): List<Location> {
        val searchedNotes = mutableListOf<Location>()
        for (item in locations) {
            if (item.description == description)
                searchedNotes.add(item)
        }
        return searchedNotes
    }
    fun findByScenes(scenes: String): List<Location> {
        val searchedNotes = mutableListOf<Location>()
        for (item in locations) {
            if (item.scenes == scenes)
                searchedNotes.add(item)
        }
        return searchedNotes
    }

    fun getSortedNew() : List<Location> = locations.sortedByDescending { it.date }
    fun getSortedOld() : List<Location> = locations.sortedBy { it.date }
    fun getSortedAZ() : List<Location> = locations.sortedBy { it.name }
    fun getSortedZA() : List<Location> = locations.sortedByDescending { it.name }

    fun getNamesList(sortedLocations: List<Location>): List<String> {
        val locationsNames = mutableListOf<String>()
        for (item in sortedLocations.indices) {
            locationsNames.add(sortedLocations[item].name)
        }
        return locationsNames
    }
}