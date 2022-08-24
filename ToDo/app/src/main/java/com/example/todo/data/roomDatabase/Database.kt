package com.example.todo.data.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.data.ToDo

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao

    companion object {
        @Volatile // immediately visible
        private var INSTANCE: ToDoDatabase? = null
        fun getDatabase(context: Context): ToDoDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


