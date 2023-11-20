package hu.bme.aut.fpyyt9.shoppinglist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var itemId: Int=0,

    @ColumnInfo(name = "category")
    val itemCategory: String,

    @ColumnInfo(name = "name")
    var itemName: String,

    @ColumnInfo(name = "description")
    var itemDescription: String,

    @ColumnInfo(name = "estimatedPriceHuf")
    var estimatedPriceHf: Int,

    @ColumnInfo(name = "status")
    var itemStatus: Boolean
)
