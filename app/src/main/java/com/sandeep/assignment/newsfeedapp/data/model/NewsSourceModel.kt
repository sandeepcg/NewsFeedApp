package com.sandeep.assignment.newsfeedapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Data class for holding News source details
 */
@Serializable
data class NewsSourceModel (
    var id: String,
    var name: String,
)