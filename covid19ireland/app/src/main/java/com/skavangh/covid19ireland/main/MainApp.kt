package com.skavangh.covid19ireland.main

import android.app.Application
import com.skavangh.covid19ireland.models.CaseModel
import com.skavangh.covid19ireland.models.CasesMemStore

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


// MAIN INIT OF APP - JUST STARTS THE APP NOTHING MORE
class MainApp : Application(), AnkoLogger {


    // CREATES GLOBALLY ACCESIBLE ARRAY CASES THAT USES CASES MEM STORE TO ADD DELETE OR UPDATE
    val cases = CasesMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Covid-19 started")

        // CREATING MOCK DATA TO START APP WITH
        var case1 = CaseModel(name = "Mary Smith",address = "9 Barrack Street",condition = "Good",status = "alive")
        var case2 = CaseModel(name = "Shane Kavanagh", address = "Main Street", condition = "Good", status = "alive")
        var case3 = CaseModel(name = "James O'Connor", address = "Ardkeen", condition = "Critical", status = "alive")
        var case4 = CaseModel(name = "Jane Fitzgerald", address = "Dunmore Road", condition = "Stable", status = "alive")

        // ADDS THE ABOVE DATA TO GLOBALLY ACCESSIBLE CASES ARRAY

        cases.create(case1)
        cases.create(case2)
        cases.create(case3)
        cases.create(case4)
    }
}
