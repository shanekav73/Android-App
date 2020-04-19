package com.skavangh.covid19ireland.models

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skavangh.covid19ireland.R
import kotlinx.android.synthetic.main.case_card.view.*

// BOILERPLATE CODE - THIS IS THE ADAPTER OF THE LIST OF CASES - IT INSTRUCTS WHAT TO DO WITH EACH CARD. CARD IS A SINGLE CASE UNIT OF CASE

interface CaseListener {
    fun onCaseClick(case: CaseModel)
    fun onCaseClickDelete(case: CaseModel)
}

class CaseAdapter constructor(private var cases: List<CaseModel>,
                                   private val listener: CaseListener) : RecyclerView.Adapter<CaseAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.case_card, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val case = cases[holder.adapterPosition]
        holder.bind(case, listener)
    }

    override fun getItemCount(): Int = cases.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(case: CaseModel,  listener : CaseListener) {

            itemView.case_name_input.text = case.name
            itemView.case_address_input.text = case.address
            itemView.case_condition_input.text = case.condition
            itemView.case_status_input.text = case.status
            itemView.updateCase.setOnClickListener { listener.onCaseClick(case) }
            itemView.deleteCase.setOnClickListener { listener.onCaseClickDelete(case) }
        }
    }
}

