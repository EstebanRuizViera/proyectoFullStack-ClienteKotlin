package com.example.flight.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Airport.TABLE_NAME, indices = [Index(value = ["country","city"],unique = true)])
data class Airport(
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "country") @NotNull val lastName: String,
    @ColumnInfo(name = "city") @NotNull val dni: String
) {
    companion object {
        const val TABLE_NAME = "airport"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}