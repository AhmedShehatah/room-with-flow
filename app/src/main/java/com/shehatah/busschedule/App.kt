package com.shehatah.busschedule

import android.app.Application
import com.shehatah.busschedule.db.AppDatabase

class App : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}