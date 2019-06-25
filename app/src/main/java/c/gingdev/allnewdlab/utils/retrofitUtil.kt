package c.gingdev.allnewdlab.utils

import android.content.Context
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.injections.retrofitInjection
import c.gingdev.allnewdlab.models.ItemModel
import retrofit2.Call
import retrofit2.http.POST

class retrofitUtil(context: Context) {
    private val retrofit = retrofitInjection
        .provideRetrofit(context.getString(R.string.Server_url))

    fun getRetrofitService() : retrofitService
            = retrofit.create(retrofitService::class.java)

    companion object {
        private var retrofitInstance: retrofitUtil? = null

        fun getInstance(context: Context): retrofitUtil
                = retrofitInstance ?: synchronized(this) {
            retrofitInstance ?: buildRetrofitInstance(context).also { retrofitInstance = it } }

        private fun buildRetrofitInstance(context: Context): retrofitUtil
                = retrofitUtil(context)
    }

    interface retrofitService {
        @POST("/sendfood")
        fun getFoodDatas(): Call<ItemModel>
        @POST("/getAllCalendar")
        fun getCalendarDatas(): Call<ItemModel>
    }
}