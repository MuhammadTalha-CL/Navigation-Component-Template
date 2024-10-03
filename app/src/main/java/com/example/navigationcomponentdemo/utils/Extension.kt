package com.example.navigationcomponentdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.navigation.NavOptions
import com.example.navigationcomponentdemo.R
import com.example.navigationcomponentdemo.apisetup.BGStateHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun NavOptions.Builder.setLeftSlideAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.slide_in_left)
        .setExitAnim(R.anim.slide_out_right)
        .setPopEnterAnim(R.anim.pop_in_right)
        .setPopExitAnim(R.anim.pop_out_left)
}

fun NavOptions.Builder.setRightSlideAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.pop_right_enter)
        .setPopExitAnim(R.anim.pop_left_exit)
}

fun NavOptions.Builder.setBottomSlideAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.slide_in_bottom)
        .setExitAnim(R.anim.slide_out_top)
        .setPopEnterAnim(R.anim.pop_in_bottom)
        .setPopExitAnim(R.anim.pop_out_top)
}

fun NavOptions.Builder.setFadeAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
}

suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): BGStateHolder<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: Response<T> = apiToBeCalled()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    BGStateHolder.Success(data = responseBody)
                } else {
                    BGStateHolder.Error(
                        errorMessage = response.message() ?: "Response Error"
                    )
                }
            } else {
                if (response.code() == 401) {
                    BGStateHolder.Error(errorMessage = "UNAUTHORIZED")
                } else if (response.code() == 404 && response.errorBody() != null) {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    BGStateHolder.Error(errorMessage = jsonObj.getString("message"))
                } else if (response.code() == 400 && response.errorBody() != null) {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    BGStateHolder.Error(errorMessage = jsonObj.getString("message"))
                } else if (response.errorBody() != null) {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    BGStateHolder.Error(errorMessage = jsonObj.getString("message"))
                } else {
                    BGStateHolder.Error(response.message())
                }
            }
        } catch (e: HttpException) {
            BGStateHolder.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            BGStateHolder.Error("Please check your network connection")
        } catch (e: Exception) {
            BGStateHolder.Error(errorMessage = "Something went wrong ${e.message}")
        }
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
    else {
        if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting) {
            return true
        }
    }
    return false
}





