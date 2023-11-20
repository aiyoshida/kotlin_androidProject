package hu.bme.aut.fpyyt9.shoppinglist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.LiveData
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.fpyyt9.shoppinglist.databinding.ActivityShoppingListBinding

class ShoppingList: AppCompatActivity() {
    //今回の試しのやつ
    private lateinit var recyclerView:RecyclerView

    private lateinit var binding: ActivityShoppingListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)

        //Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val dataSource = ItemDB.getInstance(application).itemDao
        val viewModelFactory = ShoppingListViewModelFactory(dataSource, application)
        val shoppingListViewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingListViewModel::class.java)
        binding.shoppingListViewModel = shoppingListViewModel
        binding.lifecycleOwner=this


        //試し ktに全てを繋ぐ
        recyclerView=findViewById(R.id.shopping_list_recycler)//xmlのrecyclerをセット
        recyclerView.adapter=ShoppingListAdapter(dataSource)//adapterをセット
        recyclerView.layoutManager=LinearLayoutManager(this)

        //deleteよう



//        //Adapter
//        //binding.shoppingList.layoutManager = LinearLayoutManager(this)
//        val adapter=ShoppingListAdapter()
//        binding.shoppingList.adapter=adapter
//        shoppingListViewModel.items.observe(this, Observer {
//            it?.let {
//                adapter.data = it
//                adapter.notifyDataSetChanged()
//            }
//        })


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_shopping_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_new_item -> {
                val intent = Intent(this@ShoppingList, NewItemActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}