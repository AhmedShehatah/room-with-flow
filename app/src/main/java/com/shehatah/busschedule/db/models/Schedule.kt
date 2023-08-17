package com.shehatah.busschedule.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("stop_name")
    val stopName: String,
    @ColumnInfo("arrival_time")
    val arrivalTime: Int

)