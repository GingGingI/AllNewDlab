package c.gingdev.allnewdlab.presenters

import android.content.Context
import c.gingdev.allnewdlab.constracts.calendarFragmentConstract
import c.gingdev.allnewdlab.models.ItemModel
import c.gingdev.allnewdlab.utils.retrofitUtil
import retrofit2.Call
import retrofit2.Response

class calendarPresenter(
    override val context: Context,
    override val view: calendarFragmentConstract.view)
    : calendarFragmentConstract.presenter {

    private val retrofit = retrofitUtil.getInstance(context)
    override fun receiveCalendarData() {
        retrofit.getRetrofitService().getCalendarDatas()
            .enqueue(object : retrofit2.Callback<ItemModel> {
                override fun onFailure(call: Call<ItemModel>, t: Throwable) {
                    view.failedToReceive()
                }

                override fun onResponse(call: Call<ItemModel>, response: Response<ItemModel>) {
                    response.body()?.
                        let { view.successToReceive(it) }
                }
            })
    }
}