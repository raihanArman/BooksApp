package id.co.booksapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    @SerializedName("id")
    @Expose
    var id: String ?= "",
    @SerializedName("name")
    @Expose
    var title: String ?= "",
    @SerializedName("author")
    @Expose
    var author: String ?= "",

    @SerializedName("desc")
    @Expose
    var desc: String ?= "",
    @SerializedName("rate")
    @Expose
    var rate: Float ?= 0.0f,

    @SerializedName("image")
    @Expose
    var imageUrl: String ?= "",

    @SerializedName("price_3_days")
    @Expose
    var price3Days: String ?= "",
    @SerializedName("price_5_days")
    @Expose
    var price5Days: String ?= "",
    @SerializedName("price_7_days")
    @Expose
    var price7Days: String ?= "",
    @SerializedName("stock")
    @Expose
    var stock: String ?= "",
): Parcelable