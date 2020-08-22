package com.skavangh.covid19ireland.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.skavangh.covid19ireland.R
import com.skavangh.covid19ireland.main.MainApp
import com.skavangh.covid19ireland.models.CaseAdapter
import com.skavangh.covid19ireland.models.CaseListener
import com.skavangh.covid19ireland.models.CaseModel
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class ListActivity : AppCompatActivity(), CaseListener {
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadCases()
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<AddCaseActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCaseClick(case: CaseModel) {
        startActivityForResult(intentFor<AddCaseActivity>().putExtra("case_edit", case), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadCases()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadCases() {
        showCases(app.cases.findAll())
    }

    fun showCases (cases: List<CaseModel>) {
        recyclerView.adapter = CaseAdapter(cases, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCaseClickDelete(case: CaseModel) {
        app.cases.delete(case)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}


