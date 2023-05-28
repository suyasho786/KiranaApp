package com.example.kiranaapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() ,KiranaRVadapter.KiranaItemClickInterface{
    lateinit var itemsRV:RecyclerView
    lateinit var addFAB:Button
    lateinit var list:List<KiranaItems>
    lateinit var kiranaAdapter:KiranaRVadapter
    lateinit var kiranaViewModel: KiranaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsRV = findViewById(R.id.idRvItems)

        addFAB = findViewById(R.id.mainAddBtn)
        list = ArrayList<KiranaItems>()
        kiranaAdapter = KiranaRVadapter(list,this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = kiranaAdapter
        val kiranaRepository = KiranaRepository(KiranaDataBase(this))
        val factory = KiranaViewModelFactory(kiranaRepository)
        kiranaViewModel = ViewModelProvider(this,factory).get(KiranaViewModel::class.java)
        kiranaViewModel.getAllKiranaItems().observe(this, Observer {
            kiranaAdapter.list = it
            kiranaAdapter.notifyDataSetChanged()
        })
        addFAB.setOnClickListener {
            openDialog()
        }
    }
    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.kirana_add_dialog)
        val canclBtn = dialog.findViewById<Button>( R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>( R.id.idBtnAdd)
        val itemEdt = dialog.findViewById<EditText>( R.id.idEdItemName)
        val itemPriceEdt = dialog.findViewById<EditText>( R.id.idEdItemPrice)
        val itemQtyEdt = dialog.findViewById<EditText>( R.id.idEdItemQty)
        canclBtn.setOnClickListener{
            dialog.dismiss()
        }
        addBtn.setOnClickListener{

            val itemName : String = itemEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val itemQty : String = itemQtyEdt.text.toString()
            val qty : Int = itemQty.toInt()
            val price : Int = itemPrice.toInt()
            if(itemName.isNotEmpty()&&itemPrice.isNotEmpty()&&itemQty.isNotEmpty()){
                Log.e("TAG","if entered")
                val items = KiranaItems(itemName,qty,price)
                kiranaViewModel.insert(items)
                Toast.makeText(this,"Item inserted",Toast.LENGTH_SHORT).show()
                kiranaAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(this,"Please enter all data",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
    override fun onItemClick(kiranaItems: KiranaItems) {
        kiranaViewModel.delete(kiranaItems)
        kiranaAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted..",Toast.LENGTH_SHORT).show()
    }
}