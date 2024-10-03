package com.example.navigationcomponentdemo.ui.frgaments.creditcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationcomponentdemo.apisetup.RetrofitInstance
import com.example.navigationcomponentdemo.apisetup.BGStateHolder
import com.example.navigationcomponentdemo.utils.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class CreditCardViewModel : ViewModel() {
    private val creditCardApi: CreditCardApi =
        RetrofitInstance.getInstance().create(CreditCardApi::class.java)

    var creditCardData: MutableStateFlow<BGStateHolder<CreditCardDataModel>> =
        MutableStateFlow(BGStateHolder.Empty())

    fun getCreditCardData() {
        viewModelScope.launch {
            creditCardData.value = safeApiCall { creditCardApi.getCreditCardData() }
        }
    }

}