package id.co.booksapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Transaction(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("user_id")
    @Expose
    var userId: String ?= "",

    @SerializedName("book_id")
    @Expose
    var bookId: String ?= "",

    @SerializedName("user")
    @Expose
    var user: Users,

    @SerializedName("book")
    @Expose
    var book: Book,

    @SerializedName("created_at")
    @Expose
    var date: Date,

    @SerializedName("date_return")
    @Expose
    var dateReturn: Date,

    @SerializedName("days")
    @Expose
    var days: String ?= "",
    @SerializedName("total")
    @Expose
    var total: String ?= ""
): Parcelable