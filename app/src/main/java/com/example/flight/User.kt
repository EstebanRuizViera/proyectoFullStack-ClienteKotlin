package com.example.pruebaslogin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = User.TABLE_NAME, indices = [Index(value = ["email"],unique = true)])
data class User(
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "lastname") @NotNull val lastName: String,
    @ColumnInfo(name = "dni") @NotNull val dni: String,
    @ColumnInfo(name = "phone") val phone: String? = null,
    @ColumnInfo(name = "email") @NotNull val email: String,
    @ColumnInfo(name = "password") @NotNull val password: String,
    @ColumnInfo(name = "token") @NotNull val token: String
) {
    companion object {
        const val TABLE_NAME = "user"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}