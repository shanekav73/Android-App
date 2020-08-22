package com.skavangh.covid19ireland.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CaseModel(var id: Long = 0,
                     var name: String = "",
                     var address: String = "",
                     var condition: String = "",
                     var status: String = "") : Parcelable