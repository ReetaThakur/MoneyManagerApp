package com.example.moneymanagerapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagerapp.R
import com.example.moneymanagerapp.models.Item
import com.example.moneymanagerapp.models.ItemAppDAO
import com.example.moneymanagerapp.models.ItemRoomDatabase
import com.example.moneymanagerapp.repository.ItemRepo
import com.example.moneymanagerapp.request.LoginRequestModel
import com.example.moneymanagerapp.response.Status
import com.example.moneymanagerapp.viewmodels.ItemFactory
import com.example.moneymanagerapp.viewmodels.ItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() , OnItemClicked {
    lateinit var itemAdapter: ItemAdapter
    private var itemList= mutableListOf<Item>()
    lateinit var recyclerView: RecyclerView
    lateinit var roomDb:ItemRoomDatabase
    lateinit var itemDao:ItemAppDAO

    lateinit var viewModel:ItemViewModel
    //private var databaseHandler=DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        recyclerView=findViewById(R.id.recyclerView)
        roomDb=ItemRoomDatabase.getDatabaseObject(this)
        itemDao=roomDb.getItemDAO()



        //it is a way to create viewModel object when it not depent any other object
       // viewModel=ViewModelProviders.of(this).get(ItemViewModel::class.java)
        val repo=ItemRepo(itemDao)
        val factory=ItemFactory(repo)
        viewModel=ViewModelProviders.of(this,factory).get(ItemViewModel::class.java)

        val loginRequestModel=LoginRequestModel( email= "pradeep1706108@gmail.com",
        password = "dhakhar")

      /*  viewModel.userLogin(loginRequestModel).observe(this, Observer {
            val  response=it

            when(response.status) {
                Status.SUCCESS ->{
                   val name= response.data?.user?.name
                    val email=response.data?.user?.email
                    longToast("$name and $email")
                }
                Status.ERROR ->{
                    val error=response.message
                    longToast("$error")
                }
                Status.LOADING ->{

                }
            }
        })*/

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
           /* val ree=intent
            var iName=intent.getStringExtra("Name") as String
            var iType=intent.getStringExtra("Type") as String
            var iPrice=intent.getStringExtra("Price" )as String*/
            val newItem=Item("Pen","Store","8765")
            viewModel.addTask(newItem)
           /* CoroutineScope(Dispatchers.IO).launch {
                itemDao.addItem(newItem)
            }*/

            /*var ree=intent
            var itemName=ree.getStringExtra("Name")
            var itemType=ree.getStringExtra("Type")
            var itemPrice=ree.getStringExtra("Price")*/
           // databaseHandler.insertItem("car","vehical","203948")
           // updatedUI()
        }

       // itemList=databaseHandler.getAllItem()

        // getAllItem fun fetch the data from database and we are storeing that data into itemList and that itemList we are passing to adapter
        // or do this also itemList.addAll(databaseHandler.getAllItem())

        itemAdapter= ItemAdapter(this,itemList,this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=itemAdapter

        viewModel.getItemFromDB().observe(this, Observer {
            itemList.clear()
            itemList.addAll(it)
            itemAdapter.notifyDataSetChanged()
        })
         viewModel.getItemFromAPI()
       //for Dao objects
      /*  CoroutineScope(Dispatchers.Main).launch {
            itemDao.getItems().observe(this@MainActivity, Observer {
                val items=it
                itemList.clear()
                itemList.addAll(items)
                itemAdapter.notifyDataSetChanged()
            })*/
        }

    override fun edit(item: Item) {
        val newName="Bike"
        val newType="vehical"
        val newPrice="4000"
        item.itemName=newName
        item.itemType=newType
        item.itemPrice=newPrice
        viewModel.updateItem(item)

    }

    override fun delete(item: Item) {
        viewModel.deleteItem(item)
    }

}



  //  override fun edit(item: Item) {
      //  val newName="Bike"
      //  val newType="vehical"
      //  val newPrice="4000"
       // item.iName=newName
       // item.iType=newType
        //item.iPrice=newPrice
        //databaseHandler.editItem(item)
        //updatedUI()

        // for update task
       /* item.itemName=newName  //
        item.itemType=newType
        item.itemPrice=newPrice
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.updateItem(item)
        }*/

   // }

   // override fun delete(item: Item) {
        //databaseHandler.deleteItem(item)
       // updatedUI()
       /* CoroutineScope(Dispatchers.IO).launch {
            itemDao.deleteItem(item)
        }*/

    //}
   /* fun updatedUI(){
       // val itemUpdateList=databaseHandler.getAllItem()
        itemList.clear()
        itemList.addAll(itemUpdateList)
        itemAdapter.notifyDataSetChanged()
    }*/
