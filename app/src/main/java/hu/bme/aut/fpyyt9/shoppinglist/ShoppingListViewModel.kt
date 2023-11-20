package hu.bme.aut.fpyyt9.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


//これでインスタンス化しているので、Daoが使えるよ
class ShoppingListViewModel(val database: ItemDao, application: Application) : AndroidViewModel(application) {

    ///////////////////////////////
    //1つのitem
    private var shoppingItem = MutableLiveData<Item?>()
    val items = database.getAllItems()
    val shoppingItemString = shoppingItem.map { shoppingItem -> "${shoppingItem?.itemName}" }

    init {
        initializeShoppingItem()
    }

    fun initializeShoppingItem() {
        viewModelScope.launch {
            shoppingItem.value = getItemFromDatabase()
        }
    }

    private suspend fun getItemFromDatabase(): Item? {
        var tempItem = database.getItem()
        return tempItem
    }

    //////////////////////////////////////
    //insert系
    fun insertItem() {
        viewModelScope.launch {
            val newItem = Item(0, "category", "name", "description", 100, true)
            insert(newItem)
        }
    }

    private suspend fun insert(item: Item) {
        database.insert(item)
    }

    //////////////////////////
    //delete all系
    fun deleteAllItem() {
        viewModelScope.launch {
            clearAll()
        }
    }

    private suspend fun clearAll() {
        database.clear()
    }

    fun goToNewItem() {
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            database.delete(item)
        }


    }
}











