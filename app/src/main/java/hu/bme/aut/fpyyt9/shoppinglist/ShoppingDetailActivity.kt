package hu.bme.aut.fpyyt9.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.fpyyt9.shoppinglist.databinding.ActivityShoppingDetailBinding

class ShoppingDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_detail)

        val dataSource = ItemDB.getInstance(application).itemDao
        val viewModelFactory = ShoppingDetailViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingDetailViewModel::class.java)

        binding.shoppingDetailViewModel = viewModel
        binding.lifecycleOwner = this

        val itemName = intent.getStringExtra("ITEM_NAME")
        itemName?.let {
            viewModel.loadItemByName(it)
        }

    }
}
