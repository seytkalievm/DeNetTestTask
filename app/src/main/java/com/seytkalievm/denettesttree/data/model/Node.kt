package com.seytkalievm.denettesttree.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "nodes_table")
data class Node(
    @PrimaryKey @ColumnInfo val id: String = UUID.randomUUID().toString(),
    @ColumnInfo val parent: String = "null",
    @ColumnInfo val children: List<String> = emptyList(),
) {
    fun getName() = this.hashCode().toString(2).takeLast(20)
}