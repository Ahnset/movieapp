package com.icgen.movieapp.common.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey()
    val id: Int,
    val title: String,
    val posterPath: String?
)
