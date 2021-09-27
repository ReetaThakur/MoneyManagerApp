package com.example.moneymanagerapp.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemAppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllItems(item: ArrayList<Item>)

    @Query("select * from itemTable")
    fun getItems():LiveData<List<Item>>

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)


}