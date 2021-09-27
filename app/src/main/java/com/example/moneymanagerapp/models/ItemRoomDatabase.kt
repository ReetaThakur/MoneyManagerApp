package com.example.moneymanagerapp.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Item::class],version = 1)
abstract class ItemRoomDatabase:RoomDatabase() {

    //it will return dao object
    abstract fun getItemDAO():ItemAppDAO // whose implement this class will override this function

    //create singlton class that only once create object
    companion object{
        private var INSTANCE:ItemRoomDatabase?=null
        fun getDatabaseObject(context: Context):ItemRoomDatabase{
            if (INSTANCE==null){
                val builder=Room.databaseBuilder(context.applicationContext,ItemRoomDatabase::class.java,"item_database")
                builder.fallbackToDestructiveMigration()
                INSTANCE=builder.build()
                return INSTANCE!!
            }else{
                return INSTANCE!!
            }

        }
    }
}