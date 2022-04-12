package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list.OnAdapterItemClickedListener

class CodesListAdapter(private val codesList:MutableList<Code>, private val onAdapterItemClickedListener: OnAdapterItemClickedListener): RecyclerView.Adapter<CodesListAdapter.CodesListViewHolder>() {

    inner class CodesListViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val layout:LinearLayout = view.findViewById(R.id.llList)
        val codeBitmap: ImageView = layout.findViewById(R.id.ivCode)
        val codeOwner: TextView = layout.findViewById(R.id.tvCodeOwner)
        val code: TextView = layout.findViewById(R.id.tvCode)
        private val editImageButton:ImageButton = view.findViewById(R.id.ibDeleteCodeList)

        init {
            codeBitmap.visibility = View.GONE

            editImageButton.setOnClickListener {

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
        holder.codeBitmap.setImageBitmap(
            holder.generateCodeBitmap(codeItem.code)
        )
    }

    override fun getItemCount(): Int {
        return codesList.size
    }
}