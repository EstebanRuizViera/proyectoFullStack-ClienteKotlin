package com.example.flight.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Flight.TABLE_NAME)
data class Flight(
    @ColumnInfo(name = "empty_seats") @NotNull val name: String,
    @ColumnInfo(name = "lastname") @NotNull val lastName: String,
    @ColumnInfo(name = "available_flight") @NotNull val dni: String
) {
    companion object {
        const val TABLE_NAME = "flight"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}