package com.example.moneymanagerapp.views

import android.content.Context
import com.example.moneymanagerapp.models.Item

interface OnItemClicked {
    fun edit(item: Item)

    fun delete(item: Item)
}