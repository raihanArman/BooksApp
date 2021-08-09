package id.co.booksapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("number")
    @Expose
    var number: String ?= ""
)