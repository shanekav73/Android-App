package com.skavangh.covid19ireland.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import com.skavangh.covid19ireland.helpers.*
import java.util.*

val JSON_FILE = "cases.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CaseModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CasesJSONStore : CasesStore, AnkoLogger {

    val context: Context
    var cases = mutableListOf<CaseModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CaseModel> {
        return cases
    }

    override fun create(case: CaseModel) {
        case.id = generateRandomId()
        cases.add(case)
        serialize()
    }

    override fun update(case: CaseModel) {
        var foundCase:CaseModel? = cases.find { p -> p.id == case.id }
        if (foundCase != null) {
            foundCase.name = case.name
            foundCase.address = case.address
            foundCase.condition = case.condition
            foundCase.status = case.status
        }
        serialize()
    }

    override fun delete(case: CaseModel) {
        cases.remove(case)
        serialize()
    }
    //takes in string of new case and writes it to json file.
    // when app opens and loads this json file is read from and it resides on the phone which is
    //how persistance works
    private fun serialize() {
        val jsonString = gsonBuilder.toJson(cases, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        cases = Gson().fromJson(jsonString, listType)
    }
}