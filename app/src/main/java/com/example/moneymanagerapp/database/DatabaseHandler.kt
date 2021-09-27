package com.example.moneymanagerapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.moneymanagerapp.models.Item

class DatabaseHandler(val context: Context):
    SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    // just store all values into variable like table name, id etc so can not hard code values
    companion object{
        val DB_NAME="itemDatabase"
        val DB_VERSION=1
        val TABLE_NAME="itemTable"
        val ID="id"
        val ITEM_NAME="itemName"
        val ITEM_TYPE="itemType"
        val ITEM_PRICE="itemPrice"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        //equal to create table tableName(coloms)
        val CREATE_TABLE_QUERY="CREATE TABLE $TABLE_NAME" +
                "($ID INTEGER PRIMARY KEY, $ITEM_NAME TEXT," +
                " $ITEM_TYPE TEXT, $ITEM_PRICE TEXT)"

        db?.execSQL(CREATE_TABLE_QUERY) // for execute our query

    }
    fun insertItem(itemName:String,itemType:String,itemPrice:String){
        val db=writableDatabase //for write in database use writableDatabase
         val values=ContentValues() // for storing data into table as key and value pair
        values.put(ITEM_NAME,itemName) // ITEM_NAME field put itemName value
        values.put(ITEM_TYPE,itemType)
        values.put(ITEM_PRICE,itemPrice)

       val idValues= db.insert(TABLE_NAME,null,values) // insert data into table
        // insert fun return long values that is id of inserted data
        if (idValues.toInt()==-1){
            Toast.makeText(context,"Failed to insert data",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Success to insert data",Toast.LENGTH_SHORT).show()

        }
    }

    // this fun return all item that present inside class , put it into item object and return as list
    fun getAllItem():MutableList<Item>{
        val itemList= mutableListOf<Item>() // for storing our data into list
        val db=readableDatabase
        val query="select * from $TABLE_NAME" // query for select all data from our table

       val cursor= db?.rawQuery(query,null) //rawQuery is return cursor object

        if (cursor!=null && cursor.count>0){
            cursor.moveToFirst() // start point first item into our table

            do {
                val id=cursor.getInt(cursor.getColumnIndexOrThrow(ID))// these lines insert all coloms of perticular row into variables
                val itemName=cursor.getString(cursor.getColumnIndexOrThrow(ITEM_NAME))
                val itemType=cursor.getString(cursor.getColumnIndexOrThrow(ITEM_TYPE))
                val itemPrice=cursor.getString(cursor.getColumnIndexOrThrow(ITEM_PRICE))

               // val item= Item()  // create item object and put these into itemlist and return itemlist
               // item.id=id
              //  item.iName=itemName
                //item.iType=itemType
                //item.iPrice=itemPrice
                //itemList.add(item)

            }while (cursor.moveToNext()) // move cursor to next value, it will run till data all read till last line
        }
        return itemList
    }

    //for editing the item
    fun editItem(item: Item){
        val db=writableDatabase
        val contentValues=ContentValues()
       // contentValues.put(ITEM_NAME,item.iName)
       // contentValues.put(ITEM_TYPE,item.iType)
       // contentValues.put(ITEM_PRICE,item.iPrice)
        val query= "id=${item.id}"  // this is query for selecting that id where we want to update item
        //id is member of item class thats why we are using item.id
       val result= db.update(TABLE_NAME,contentValues,query,null)
        if(result==1){
            Toast.makeText(context,"Item Updated",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Update Failed",Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteItem(item: Item){
        val db=writableDatabase
        val result=db.delete(TABLE_NAME,"id=${item.id}",null)
        if (result==1){
            Toast.makeText(context,"Item Deleted",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Deletion Failed",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}