package com.beomsu317.cocktailrecipeapp.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromListWithStringPair(value: List<Pair<String, String?>>?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListWithStringPair(value: String): List<Pair<String, String?>> {
        return Json.decodeFromString<List<Pair<String, String?>>>(value)
    }
}