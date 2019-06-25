package c.gingdev.allnewdlab.presenters

import android.content.Context
import c.gingdev.allnewdlab.constracts.foodFragmentConstract
import c.gingdev.allnewdlab.models.ItemModel
import c.gingdev.allnewdlab.utils.retrofitUtil
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class foodPresenter(
    override val context: Context,
    override val view: foodFragmentConstract.view)
    : foodFragmentConstract.presenter {

    private val retrofit = retrofitUtil.getInstance(context)

    override fun receiveFoodData() {
        retrofit.getRetrofitService().getFoodDatas()
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