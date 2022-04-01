package com.gmail.bodziowaty6978.kodyzabka.feature_code.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code

@Database(
    entities = [Code::class],
    version = 1
)
abstract class CodeDatabase: RoomDatabase() {
    abstract val codeDao:CodeDao

    companion object{
        const val DATABASE_NAME = "code_db"
    }
}