package id.co.booksapp.request

import id.co.booksapp.model.*
import id.co.core.data.response.ResponseLogin
import id.co.core.data.response.Wrapper
import retrofit2.http.*

interface ApiRequest {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Wrapper<ResponseLogin>


    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("telp") telp: String,
        @Field("password") password: String,
    ): Wrapper<ResponseLogin>

    @GET("book/popular")
    suspend fun getPopularBook(
        @Header("Authorization") token: String
    ): Wrapper<List<Book>>

    @GET("book/category/{id}")
    suspend fun getBookByCategory(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<List<Book>>

    @GET("book")
    suspend fun getAllBook(
        @Header("Authorization") token: String
    ): Wrapper<List<Book>>

    @GET("category")
    suspend fun getAllCategory(
        @Header("Authorization") token: String
    ): Wrapper<List<Category>>


    @GET("contact")
    suspend fun getContact(
        @Header("Authorization") token: String
    ): Wrapper<Contact>


    @FormUrlEncoded
    @POST("transaction/add")
    suspend fun addTransaction(
        @Header("Authorization") token: String,
        @Field("user_id") userId: String,
        @Field("book_id") bookId: String,
        @Field("date_return") dateReturn: String,
        @Field("days") days: String,
        @Field("total") total: String,
    ): Wrapper<Transaction>

    @GET("transaction/user/{id}")
    suspend fun getTransactionByUser(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<List<Transaction>>

    @GET("book/search")
    suspend fun getBookSearch(
        @Header("Authorization") token: String,
        @Query("search") search: String
    ): Wrapper<List<Book>>

    @GET("user")
    suspend fun getUsersById(
        @Header("Authorization") token: String
    ): Wrapper<Users>

}