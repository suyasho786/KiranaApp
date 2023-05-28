package com.example.kiranaapp
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class KiranaRVadapter
    (var list :List<KiranaItems>,
     private val kiranaItemClickInterface:KiranaItemClickInterface
     ): RecyclerView.Adapter<KiranaRVadapter.KiranaViewHolder>()
{

    inner class KiranaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val QtyTV = itemView.findViewById<TextView>(R.id.idTVItemQty)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idTVDelete)

    }


    interface KiranaItemClickInterface{
        fun onItemClick(kiranaItems: KiranaItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KiranaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kiranav_rv_item,parent,false)
        return KiranaViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size.toInt()
    }

    override fun onBindViewHolder(holder: KiranaViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.QtyTV.text = list.get(position).itemQty.toString()
        holder.rateTV.text ="Rs. " +  list.get(position).itemPrice.toString()
        val itemTotal : Int = list[position].itemPrice* list[position].itemQty
        holder.amountTV.text = "Rs ."+itemTotal
        holder.deleteTV.setOnClickListener{
            kiranaItemClickInterface.onItemClick(list.get(position))
        }
    }
}