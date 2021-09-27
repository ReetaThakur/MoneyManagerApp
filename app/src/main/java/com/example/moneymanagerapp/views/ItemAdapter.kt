package com.example.moneymanagerapp.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagerapp.R
import com.example.moneymanagerapp.models.Item

class ItemAdapter(val context: Context, val itemList: MutableList<Item>,val listner: OnItemClicked):RecyclerView.Adapter<ItemAdapter.ItemViewModule>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewModule {
        val inflater=LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.item_raw_layout,parent,false)
        return ItemViewModule(view)
    }

    override fun onBindViewHolder(holder: ItemViewModule, position: Int) {
        val item=itemList.get(position)
        holder.itemName.text= item.itemName
        holder.itemType.text=item.itemType
        holder.itemPrice.text=item.itemPrice

        holder.editTv.setOnClickListener {
            listner.edit(item)
        }
        holder.deleteTv.setOnClickListener {
            listner.delete(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewModule(itemView:View):RecyclerView.ViewHolder(itemView){
        var itemName:TextView
        var itemType:TextView
        var itemPrice:TextView
        var editTv:TextView
        var deleteTv:TextView
        init {
            itemName=itemView.findViewById(R.id.tvitemName)
            itemType=itemView.findViewById(R.id.tvitemType)
            itemPrice=itemView.findViewById(R.id.tvitemPrice)
            editTv=itemView.findViewById(R.id.editTv)
            deleteTv=itemView.findViewById(R.id.deleteTv)
        }

    }
}