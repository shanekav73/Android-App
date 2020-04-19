package com.skavangh.covid19ireland.models
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L
// ADD UPDATE DELETE BOILERPLATE. USED IN ADD CASE AND LIST CASE TO ADD REMOVE OR UPDATE - PROVIDES LOGIC FOR MY CRUD
internal fun getId(): Long {
    return lastId++
}

class CasesMemStore : CasesStore, AnkoLogger {
    override fun delete(case: CaseModel) {
        var foundCase: CaseModel? = cases.find{ p -> p.id == case.id }
        cases.remove(foundCase)
    }

    val cases = ArrayList<CaseModel>()

    override fun findAll(): List<CaseModel> {
        return cases
    }

    override fun create(case: CaseModel) {
        case.id = getId()
        cases.add(case)
        logAll()
    }

    override fun update(case: CaseModel) {
        var foundCase:CaseModel? = cases.find { p -> p.id == case.id }
        if (foundCase != null) {
            foundCase.name = case.name
            foundCase.address = case.address
            foundCase.condition = case.condition
            foundCase.status = case.status
            logAll()
        }
    }
    fun logAll() {
        cases.forEach { info("${it}") }
    }
}