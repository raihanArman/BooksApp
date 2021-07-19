package id.co.booksapp.model

data class Book(
    var id: String ?= "",
    var title: String ?= "",
    var author: String ?= "",
    var desc: String ?= "",
    var rate: Float ?= 0.0f,
    var imageUrl: String ?= ""
)