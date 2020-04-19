package com.skavangh.covid19ireland.models

// INTERFACE - NOT SURE WHY I NEED IT BUT I JUST FOLLOW PRACTICALS
interface CasesStore {
    fun findAll(): List<CaseModel>
    fun create(case: CaseModel)
    fun update(case: CaseModel)
    fun delete(case: CaseModel)
}
