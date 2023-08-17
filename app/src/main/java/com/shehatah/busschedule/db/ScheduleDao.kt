package com.shehatah.busschedule.db

import androidx.room.Dao
import androidx.room.Query
import com.shehatah.busschedule.db.models.Schedule
import kotlinx.coroutines.flow.Flow


@Dao
interface ScheduleDao {

    @Query("select * from schedule order by arrival_time asc")
    fun getAll(): Flow<List<Schedule>>

    @Query("select * from schedule where stop_name = :stopName order by arrival_time asc")
    fun getByStopName(stopName: String): Flow<List<Schedule>>
}