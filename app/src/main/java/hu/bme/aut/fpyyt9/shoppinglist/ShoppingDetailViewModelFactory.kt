package hu.bme.aut.fpyyt9.shoppinglist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingDetailViewModelFactory(
    private val dataSource3: ItemDao,
    private val application3: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingDetailViewModel::class.java)) {
            return ShoppingDetailViewModel(dataSource3, application3) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

