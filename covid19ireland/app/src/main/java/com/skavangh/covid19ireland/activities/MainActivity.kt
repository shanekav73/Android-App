package com.skavangh.covid19ireland.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.skavangh.covid19ireland.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnViewStats.setOnClickListener() {
            startActivityForResult<ListActivity>(0)
        }
    }
}
