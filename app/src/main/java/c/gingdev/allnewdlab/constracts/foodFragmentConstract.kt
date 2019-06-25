package c.gingdev.allnewdlab.constracts

import android.content.Context
import c.gingdev.allnewdlab.models.ItemModel

interface foodFragmentConstract {
    interface view {
        fun successToReceive(item: ItemModel)
        fun failedToReceive()

    }
    interface presenter {
        val context: Context
        val view: view

        fun receiveFoodData()
    }
}