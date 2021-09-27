package com.example.moneymanagerapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneymanagerapp.repository.ItemRepo

class ItemFactory(val repo: ItemRepo) :ViewModelProvider.Factory {

   // it return viewModel object
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemViewModel(repo) as T // type cast in T
    }

}