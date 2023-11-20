package hu.bme.aut.fpyyt9.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDB : RoomDatabase() {

    abstract val itemDao: ItemDao

    companion object {

        @Volatile
        private var INSTANCE: ItemDB? = null

        fun getInstance(context: Context): ItemDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDB::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}