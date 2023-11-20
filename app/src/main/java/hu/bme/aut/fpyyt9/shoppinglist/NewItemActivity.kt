package hu.bme.aut.fpyyt9.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.fpyyt9.shoppinglist.databinding.ActivityNewItemBinding

class NewItemActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNewItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_item)

        val dataSource = ItemDB.getInstance(application).itemDao
        val viewModelFactory = NewItemViewModelFactory(dataSource, application)
        val newItemViewModel = ViewModelProvider(this, viewModelFactory).get(NewItemViewModel::class.java)

        binding.newItemViewModel = newItemViewModel
        binding.lifecycleOwner = this
    }
}
