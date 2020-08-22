package com.skavangh.covid19ireland.models

interface CasesStore {
    fun findAll(): List<CaseModel>
    fun create(case: CaseModel)
    fun update(case: CaseModel)
    fun delete(case: CaseModel)
}
