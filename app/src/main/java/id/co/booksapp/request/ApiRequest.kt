package id.co.booksapp.request

import id.co.core.data.response.ResponseLogin
import id.co.core.data.response.Wrapper
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiRequest {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Wrapper<ResponseLogin>
}