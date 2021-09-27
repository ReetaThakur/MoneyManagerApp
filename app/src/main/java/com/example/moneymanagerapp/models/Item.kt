package com.example.moneymanagerapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="itemTable")
data class Item(@ColumnInfo(name="itemName") var itemName:String,
                @ColumnInfo(name="itemType")var itemType :String,
                @ColumnInfo(name="itemPrice")var itemPrice:String) {
   @PrimaryKey(autoGenerate = true)@ColumnInfo(name="id") var id:Int?=null

}