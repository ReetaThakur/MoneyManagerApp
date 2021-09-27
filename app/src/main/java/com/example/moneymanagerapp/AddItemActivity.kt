package com.example.moneymanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moneymanagerapp.database.DatabaseHandler
import com.example.moneymanagerapp.views.MainActivity
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {
    private var databaseHandler=DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        button.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            intent.putExtra("Name",edtItemType.text.toString())
            intent.putExtra("Type",edtItemType.text.toString())
            intent.putExtra("Price",edtItemPrice.text.toString())
            startActivity(intent)
        }

    }


}
