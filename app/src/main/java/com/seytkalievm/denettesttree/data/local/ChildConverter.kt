package com.seytkalievm.denettesttree.data.local

import androidx.room.TypeConverter

class ChildConverter {
    @TypeConverter
    fun toChildren(children: List<String>): String {
        return children.joinToString(" ")
    }

    @TypeConverter
    fun fromChildren(children: String): List<String> {
        return children.split(" ").toList()
    }
}