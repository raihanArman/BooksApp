package id.co.booksapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String ?= "",

    @SerializedName("email")
    @Expose
    var email: String ?= "",

    @SerializedName("status")
    @Expose
    var status: String ?= "",

    @SerializedName("telp")
    @Expose
    var telp: String ?= "",


): Parcelable