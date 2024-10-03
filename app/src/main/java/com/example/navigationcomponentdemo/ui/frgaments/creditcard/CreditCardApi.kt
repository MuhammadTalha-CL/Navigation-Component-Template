package com.example.navigationcomponentdemo.ui.frgaments.creditcard

import retrofit2.Response
import retrofit2.http.GET

interface CreditCardApi {
    @GET("credit_cards")
    suspend fun getCreditCardData(): Response<CreditCardDataModel>
}