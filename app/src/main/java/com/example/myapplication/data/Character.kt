package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    val id: UUID,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "age")
    val age: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "history")
    val history: String,
    @ColumnInfo(name = "role")
    val role: String,
    @ColumnInfo(name = "scenes")
    val scenes: String
)
