package hu.bme.aut.fpyyt9.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item:Item)

    @Update
    suspend fun update(item:Item)

    @Delete
    suspend fun delete(item:Item)

    //get last item
    @Query("SELECT * FROM item_table ORDER BY itemId DESC LIMIT 1")
    suspend fun getItem(): Item?

    @Query("SELECT * FROM item_table WHERE name = :name LIMIT 1")
    suspend fun getItemByName(name: String): Item?



    //delete all、tableから全てを消す
    @Query("DELETE FROM item_table")
    suspend fun clear()


    //全てをlistする
    @Query("SELECT * FROM item_table ORDER BY itemId DESC")
    fun getAllItems(): LiveData<List<Item>>

}