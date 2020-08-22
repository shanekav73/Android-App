package com.skavangh.covid19ireland.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.skavangh.covid19ireland.R
import com.skavangh.covid19ireland.main.MainApp
import com.skavangh.covid19ireland.models.CaseModel
import kotlinx.android.synthetic.main.activity_add_case.*
import kotlinx.android.synthetic.main.case_card.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast


class AddCaseActivity : AppCompatActivity(), AnkoLogger {
    var case = CaseModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_case)
        app = application as MainApp

        var edit = false

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("case_edit")) {
            addCase.setText("Update")
            edit = true
            case = intent.extras.getParcelable<CaseModel>("case_edit")
            name.setText(case.name)
            address.setText(case.address)
            status.setText(case.status)
            condition.setText(case.condition)
        }

        addCase.setOnClickListener() {
            case.name = name.text.toString()
            case.address = address.text.toString()
            case.status = status.text.toString()
            case.condition = condition.text.toString()

            if (case.name.isEmpty() or case.address.isEmpty() or case.status.isEmpty() or case.condition.isEmpty()) {
                toast("Enter all details please")
            } else {
                if (edit) {
                    app.cases.update(case.copy())
                    toast("in update")
                    finish()
                } else {
                    app.cases.create(case.copy())
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_case, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}