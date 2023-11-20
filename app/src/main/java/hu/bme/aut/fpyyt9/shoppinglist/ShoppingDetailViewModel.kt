package hu.bme.aut.fpyyt9.shoppinglist

import android.app.Application
import android.content.Intent.getIntent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ShoppingDetailViewModel(val database: ItemDao, application: Application) : AndroidViewModel(application) {
    val selectedItem = MutableLiveData<Item?>()

    var data = listOf<Item>()
    init {
        // Observe changes in the database
        database.getAllItems().observeForever {
            data = it
        }
    }


    // アイテムの各フィールド用の LiveData
    val textName = MutableLiveData<String>()
    val textCategory = MutableLiveData<String>()
    val textDescription = MutableLiveData<String>()
    val textPrice = MutableLiveData<String>()
    val checkBox = MutableLiveData<Boolean>()
    val imageCategory = MutableLiveData<Int>()

    fun loadItemByName(name: String) {
        viewModelScope.launch {
            val item = getItemByName(name)
            selectedItem.value = item
            item?.let {
                textName.value = it.itemName
                textCategory.value = it.itemCategory
                textDescription.value = it.itemDescription
                textPrice.value = it.estimatedPriceHf.toString()
                checkBox.value = it.itemStatus
                imageCategory.value = getCategoryImage(it.itemCategory)
            }

        }
    }
    private fun getCategoryImage(category: String): Int {
        return when (category) {
            "rice" -> R.drawable.rice
            "book" -> R.drawable.book
            "food" -> R.drawable.food
            "snack" -> R.drawable.snack
            else -> R.drawable.splash_img
        }
    }


    private suspend fun getItemByName(name: String): Item? {
        return database.getItemByName(name)
    }
}









