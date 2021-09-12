package com.example.demodatabase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.atividade1.dataroom.IMC
import com.example.atividade1.R

class ImcListAdapter : ListAdapter<IMC, ImcListAdapter.ImcViewHolder>( ImcComparator() ) {

    private var usuarioDelete: ((IMC) -> Unit)? = null

    fun setUsuarioDelete(action: (IMC) -> Unit) {
        usuarioDelete = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ImcViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImcViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class ImcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeItemView: TextView = itemView.findViewById(R.id.nome)
        private val IMCItemView: TextView = itemView.findViewById(R.id.imc)
        private val btDelete: Button = itemView.findViewById(R.id.btDelete)

        fun bind(imc: IMC) {
            nomeItemView.text = imc.name
            IMCItemView.text = imc.result

            btDelete.setOnClickListener {
                usuarioDelete?.invoke(imc)
            }
        }
    }

    class ImcComparator : DiffUtil.ItemCallback<IMC>() {
        override fun areItemsTheSame(oldItem: IMC, newItem: IMC): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: IMC, newItem: IMC): Boolean {
            return oldItem.id == newItem.id
        }
    }
}