package com.sample.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.data.SummaryItem
import com.sample.weatherapp.databinding.LayoutSummaryRecyclerViewItemBinding
import com.sample.weatherapp.extension.listen
import com.sample.weatherapp.listener.OnRecyclerViewItemClick

class SummaryRecyclerViewAdapter(private val onRecyclerViewItemClick: OnRecyclerViewItemClick?) :
    RecyclerView.Adapter<SummaryRecyclerViewAdapter.BenefitsViewHolder>() {

    private var summaryListItems = mutableListOf<SummaryItem>()

    inner class BenefitsViewHolder(val binding: LayoutSummaryRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            LayoutSummaryRecyclerViewItemBinding.inflate(inflater, parent, false)
        return BenefitsViewHolder(binding).listen { position, type ->
            onRecyclerViewItemClick?.onItemClick(position)
        }
    }

    override fun onBindViewHolder(holder: BenefitsViewHolder, position: Int) {
        holder.binding.tvTitle.text = summaryListItems[position].title
        holder.binding.tvDetail.text = summaryListItems[position].value
    }

    override fun getItemCount() = summaryListItems.size

    fun setData(summaryListItems: List<SummaryItem>) {
        this.summaryListItems.clear()
        this.summaryListItems.addAll(summaryListItems)
        notifyDataSetChanged()
    }
}
