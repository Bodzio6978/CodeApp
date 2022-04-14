package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code

class CodesListAdapter(private val codesList:MutableList<Code>, private val onAdapterItemClickedListener: OnAdapterItemClickedListener): RecyclerView.Adapter<CodesListAdapter.CodesListViewHolder>() {

    inner class CodesListViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val code: TextView = view.findViewById(R.id.tvCodeList)
        val codeOwner: TextView = view.findViewById(R.id.tvCodeOwnerList)
        private val deleteImageButton:ImageButton = view.findViewById(R.id.ibDeleteCodeList)

        init {
            deleteImageButton.setOnClickListener {
                onAdapterItemClickedListener.onAdapterItemClicked(CodeEvent.DeleteCode(code = codesList[adapterPosition]))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.code_list_layout, parent, false)
        return CodesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CodesListViewHolder, position: Int) {
        val codeItem = codesList[position]
        holder.code.text = codeItem.code
        holder.codeOwner.text = codeItem.user
    }

    override fun getItemCount(): Int {
        return codesList.size
    }
}