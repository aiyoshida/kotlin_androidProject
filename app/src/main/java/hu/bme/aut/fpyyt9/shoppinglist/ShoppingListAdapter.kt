package hu.bme.aut.fpyyt9.shoppinglist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ShoppingListAdapter(itemDao: ItemDao):RecyclerView.Adapter<ShoppingListAdapter.itemViewHolder>() {


    interface OnItemDeleteClickListener {
        fun onItemDeleteClick(item: Item)
    }
    var onItemDeleteClickListener: OnItemDeleteClickListener? = null


    //dbを組み込みたい
    var data = listOf<Item>()
    init {
        // Observe changes in the database
        itemDao.getAllItems().observeForever {
            data = it
            notifyDataSetChanged()


        }
    }


    // ViewHolderを定義
    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //ビューの参照を取得
        val nameText: TextView = itemView.findViewById(R.id.nameText)
        val categoryText: TextView = itemView.findViewById(R.id.categoryText)//これに合わせて、4つのiconを揃える。
        val imgCategory: ImageView = itemView.findViewById(R.id.item_image)
        val btnDetail: Button = itemView.findViewById(R.id.btn_view_detail)
        val btnDelete: Button = itemView.findViewById(R.id.btn_delete)
        val PurchaseCheckBox: CheckBox = itemView.findViewById(R.id.purchased_checkBox)

        init {
            btnDetail.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ShoppingDetailActivity::class.java)
                intent.putExtra("ITEM_NAME", nameText.text.toString()) // キーを "ITEM_NAME" に変更
                context.startActivity(intent)


            }


        }
        fun bind(item:Item){
            nameText.text= item.itemName
            categoryText.text=item.itemCategory
            imgCategory.setImageResource(when (item.itemCategory) {
                "rice" -> R.drawable.rice
                "book" -> R.drawable.book
                "food" -> R.drawable.food
                "snack" -> R.drawable.snack
                else -> R.drawable.splash_img
            })
            PurchaseCheckBox.setChecked(item.itemStatus)
        }
    }

    //1 1行分のviewを作る。
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return itemViewHolder(view)
    }

    //listのなかの、position番目のdataをxmlに渡してね
    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    //アダプターがいくつ持っているか
    override fun getItemCount() = data.size
    //


}

private fun Intent.putExtra(s: String, nameText: TextView) {

}
