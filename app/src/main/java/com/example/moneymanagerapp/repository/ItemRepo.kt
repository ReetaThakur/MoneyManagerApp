package com.example.moneymanagerapp.repository

import androidx.lifecycle.LiveData
import com.example.moneymanagerapp.fetchdata.GetItemResponseModel
import com.example.moneymanagerapp.models.Item
import com.example.moneymanagerapp.models.ItemAppDAO
import com.example.moneymanagerapp.request.LoginRequestModel
import com.example.moneymanagerapp.request.LoginResponse
import com.example.moneymanagerapp.response.ItemAPI
import com.example.moneymanagerapp.response.Network
import com.example.moneymanagerapp.response.Resource
import com.example.moneymanagerapp.response.ResponseHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemRepo(val itemDAO:ItemAppDAO) {


    //for api calling
    private val api: ItemAPI =Network.getRetrofit().create(ItemAPI::class.java)
    private val responseHandler=ResponseHandler()
    //token for api calling
    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MGE0YmI3OTAzMjdlN2MwNmE2MTk1ODYiLCJpYXQiOjE2MzIxMzg2ODR9.cTxpYQrTfvramIOSPih6b1hJO_x1G-V2GmaRnTYSjU0"

    suspend fun login(loginRequestModel: LoginRequestModel):Resource<LoginResponse>{
       return try{
            val response= api.login(loginRequestModel)
             responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }

    fun getRemoteItem(){

        CoroutineScope(Dispatchers.IO).launch {
            val response=api.getItemFromAPI(token)
            saveToDB(response)
        }
    }

    private fun saveToDB(response: GetItemResponseModel) {

        val listItem=ArrayList<Item>()
        response.forEach {
            val newItem=Item(it.title,it.description,it._id)
            listItem.add(newItem)
        }
        itemDAO.addAllItems(listItem)
    }

    fun addTaskToRoom(item: Item){
        CoroutineScope(Dispatchers.IO).launch {
            itemDAO.addItem(item)
        }
    }

    fun getAllItems():LiveData<List<Item>>{
       return itemDAO.getItems()
    }

    fun updateInRepo(item: Item){
        CoroutineScope(Dispatchers.IO).launch {
            itemDAO.updateItem(item)
        }
    }

    fun deleteToReop(item: Item){
        CoroutineScope(Dispatchers.IO).launch {
            itemDAO.deleteItem(item)
        }
    }
}