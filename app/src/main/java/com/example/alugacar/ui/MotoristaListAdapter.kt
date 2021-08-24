package com.example.alugacar.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alugacar.R
import com.example.alugacar.db.MotoristaEntity
import kotlinx.android.synthetic.main.motorista_item.view.*

class MotoristaListAdapter(
    private val motoristas: List<MotoristaEntity>
) : RecyclerView.Adapter<MotoristaListAdapter.MotoristaListViewHolder>(){

    var onItemClick: ((entity: MotoristaEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotoristaListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.motorista_item, parent, false)
        return MotoristaListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MotoristaListViewHolder, position: Int) {
        holder.bindView(motoristas[position])
    }

    override fun getItemCount() = motoristas.size

    inner class MotoristaListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textViewMotoristaName: TextView = itemView.text_motorista_name
        private val textViewMotoristaDescribe: TextView = itemView.text_motorista_describe

        fun bindView(motorista: MotoristaEntity){
            textViewMotoristaName.text = motorista.name
            textViewMotoristaDescribe.text = motorista.describe
            itemView.setOnClickListener{
                onItemClick?.invoke(motorista)
            }
        }
    }
}