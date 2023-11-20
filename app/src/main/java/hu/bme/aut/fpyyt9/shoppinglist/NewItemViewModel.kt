package hu.bme.aut.fpyyt9.shoppinglist

import android.app.Application
import android.content.Intent
import android.provider.SyncStateContract.Helpers.insert
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


//役割：new item activityのデータ保持。
class NewItemViewModel(val database: ItemDao, application: Application) : AndroidViewModel(application)
{
    var data = listOf<Item>()
    init {
        // Observe changes in the database
        database.getAllItems().observeForever {
            data = it
        }
    }


    //1つのitem
    private var shoppingItem = MutableLiveData<Item?>()
    //val shoppingItemString=shoppingItem.map{ shoppingItem ->"${shoppingItem?.itemName}" }

    init{
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
    private suspend fun insert(item: Item) {
        database.insert(item)
    }
    ///////////////////////////////

    //Livedataの値
    var textCategory=MutableLiveData<String>()
    var textName=MutableLiveData<String>()
    var textDescription=MutableLiveData<String>()
    var textPrice=MutableLiveData<String>()
    var booleanStatus=MutableLiveData<Boolean>()



    fun onAddItemClicked(){
        viewModelScope.launch {

            val tempItem = Item(
                0,
                textCategory.value.toString() ?: "",
                textName.value.toString() ?: "",
                textDescription.value.toString() ?: "",
                textPrice.value?.toIntOrNull() ?: 0,
                booleanStatus.value?:false
            )
            insert(tempItem)

        }
    }


}