package hu.bme.aut.fpyyt9.shoppinglist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingListViewModelFactory(
    private val dataSource: ItemDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    //エラーが出るので、?消しちゃったけどいいかな？最初の方、<T : ViewModel?>
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListViewModel::class.java)) {
            return ShoppingListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}