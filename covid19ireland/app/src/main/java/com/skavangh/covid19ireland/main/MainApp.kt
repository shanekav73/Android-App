package com.skavangh.covid19ireland.main

import android.app.Application
import com.skavangh.covid19ireland.models.CasesJSONStore
import com.skavangh.covid19ireland.models.CasesStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {
    lateinit var cases: CasesStore

    override fun onCreate() {
        super.onCreate()
        cases = CasesJSONStore(applicationContext)
        info("Covid-19 started")
    }
}