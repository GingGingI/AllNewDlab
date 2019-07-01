package c.gingdev.allnewdlab.presenters

import android.content.Context
import android.util.Log
import c.gingdev.allnewdlab.constracts.scheduleFragmentConstract
import c.gingdev.allnewdlab.models.ItemModel
import c.gingdev.allnewdlab.utils.preferenceUtil
import c.gingdev.allnewdlab.utils.retrofitUtil
import retrofit2.Call
import retrofit2.Response

class schedulePresenter(
    override val context: Context,
    override val view: scheduleFragmentConstract.view)
    : scheduleFragmentConstract.presenter {

    private val retrofit = retrofitUtil.getInstance(context)
    override fun receiveScheduleData() {
        retrofit.getRetrofitService().getScheduleDatas(buildParams())
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

    private val pref = preferenceUtil.getInstance(context)
    private fun buildParams(): Map<String, String>
            = HashMap<String, String>().apply {
        put("grade", pref.PREF_GRADE!!)
        put("classNum", pref.PREF_CLASS!!) }
}