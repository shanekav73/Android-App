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

    // CREATING LOCAL CASE MODEL
    var case = CaseModel()
    // APP.CASES WILL ALLOW US TO ACCESS GLOBAL CASES ARRAY
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_case)
        app = application as MainApp

        // SETTING EDIT OPTION TO DEFAULT NO
        var edit = false

        //JUST SOME TOOLBAR STUFF NOTHING MAJOR
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        //TOOLBAR BACK ARROW - FOLLOWED WHAT ROB SAID ON CHANNEL
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // CASES CLASS USES PARCELABLE - WHICH MEANS IT CAN BE PASSED AROUND ACTIVITIES
        // IF THERE IS A CASE PASSED TO CREATE CASE WE ARE USING IT TO AUTOFILL CREATE FIELDS
        // SO THAT USER CAN EDIT THESE FIELDS INSTEAD OF CREATING NEW CASE
        // ALSO, BUTTON CREATE CASE HAS BEEN CHANGED TO UPDATE
        if (intent.hasExtra("case_edit")) {
            addCase.setText("Update")
            edit = true
            case = intent.extras.getParcelable<CaseModel>("case_edit")
            name.setText(case.name)
            address.setText(case.address)
            status.setText(case.status)
            condition.setText(case.condition)
        }


        // ADDING A CLICL LISTENER TO ADDCASE BUTTON. IT MEANS THAT BUTTON IS CLICKABLE
        addCase.setOnClickListener() {

            //CREATING CASE OBJECT BASED ON TEXT ENTERED INTO FIELDS
            case.name = name.text.toString()
            case.address = address.text.toString()
            case.status = status.text.toString()
            case.condition = condition.text.toString()

            // SIMPLE CHECK IF ANY OF FIELDS IS EMPTY THROW AN ERROR
            if (case.name.isEmpty() or case.address.isEmpty() or case.status.isEmpty() or case.condition.isEmpty()) {
                toast("Enter all details please")
            } else {

                // IF WE ARE IN EDIT MORE WE WANT TO USE UPDATE FUNCTION FROM CASE MEM STORE
                if (edit) {
                    app.cases.update(case.copy())
                    toast("in update")
                    finish()
                    // IF NOT IN EDIT, WE ARE SIMPLY CREATING NEW CASE
                } else {
                    app.cases.create(case.copy())
                    finish()
                }
            }

        }

    }

// BOILERPLATE, FOLLOWED THIS FROM LECTURES AND CHAT CHANNEL
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_case, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // when + is pressed we are redirecting to another activity ?
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}