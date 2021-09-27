package com.example.moneymanagerapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moneymanagerapp.models.Item
import com.example.moneymanagerapp.repository.ItemRepo
import com.example.moneymanagerapp.request.LoginRequestModel
import com.example.moneymanagerapp.request.LoginResponse
import com.example.moneymanagerapp.response.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(val repo: ItemRepo): ViewModel() {

    fun userLogin(loginRequestModel: LoginRequestModel):LiveData<Resource<LoginResponse>>{

       return liveData(Dispatchers.IO){
           val result= repo.login(loginRequestModel)
           emit(result)
       }
    }

    fun getItemFromAPI(){
        repo.getRemoteItem()
    }

    fun addTask(item:Item){
        repo.addTaskToRoom(item)
    }

    fun getItemFromDB():LiveData<List<Item>>{
       return repo.getAllItems()
    }

    fun updateItem(item: Item){
        repo.updateInRepo(item)
    }

    fun deleteItem(item: Item){
        repo.deleteToReop(item)
    }
}