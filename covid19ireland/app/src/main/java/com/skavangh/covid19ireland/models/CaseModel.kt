package com.skavangh.covid19ireland.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// CASE CLASS - BLUEPRINT HOW CASE LOOKS LIKE AND WHAT IT CONTAINS
@Parcelize
data class CaseModel(var id: Long = 0,
                     var name: String = "",
                     var address: String = "",
                     var condition: String = "",
                     var status: String = "") : Parcelable