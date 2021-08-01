package id.co.booksapp.model

import java.util.*

data class History(
    var id: String ?= "",
    var date: Date,
    var book: Book
)