package com.example.myapplication.data

import java.util.UUID

object UtilObject {
    private var curNoteType: String = ""
    private var curItemId: UUID = UUID.randomUUID()
    private var curContext: String = ""

    fun getCurNoteType(): String {
        return curNoteType
    }
    fun getCurItemId(): UUID {
        return curItemId
    }
    fun getCurContext(): String {
        return curContext
    }

    fun setCurNoteType(newNoteType: String) {
        this.curNoteType = newNoteType
    }
    fun setCurItemId(newCurItemId: UUID) {
        this.curItemId = newCurItemId
    }
    fun setCurContext(newCurContext: String) {
        this.curContext = newCurContext
    }
}