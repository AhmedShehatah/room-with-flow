package com.shehatah.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shehatah.busschedule.db.ScheduleDao
import com.shehatah.busschedule.db.models.Schedule
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    fun fullSchedule():Flow< List<Schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): Flow<List<Schedule>> = scheduleDao.getByStopName(name)

}

@Suppress("UNCHECKED_CAST")
class BusScheduleViewModelFactory(private val scheduleDao: ScheduleDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}