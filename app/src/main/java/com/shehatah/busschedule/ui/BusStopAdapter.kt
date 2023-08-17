package com.shehatah.busschedule.ui


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shehatah.busschedule.databinding.BusStopItemBinding
import com.shehatah.busschedule.db.models.Schedule
import java.text.SimpleDateFormat
import java.util.Date

class BusStopAdapter(private val onItemClicked: (Schedule) -> Unit) :
    ListAdapter<Schedule, BusStopAdapter.ViewHolder>(DiffCallBack) {

    inner class ViewHolder(private var binding: BusStopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
            binding.stopNameTextView.text = schedule.stopName
            binding.arrivalTimeTextView.text =
                SimpleDateFormat("hh:mm a").format(Date(schedule.arrivalTime.toLong() * 1000))
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            BusStopItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }
}