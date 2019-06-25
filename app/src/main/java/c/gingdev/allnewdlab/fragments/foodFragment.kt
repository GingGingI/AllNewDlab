package c.gingdev.allnewdlab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.constracts.foodFragmentConstract
import c.gingdev.allnewdlab.models.ItemModel
import c.gingdev.allnewdlab.presenters.foodPresenter
import c.gingdev.allnewdlab.recycler.adapter.foodAdapter
import kotlinx.android.synthetic.main.fragment_layout_food.*

class foodFragment: Fragment(),
    foodFragmentConstract.view {
    companion object {
        private var Instance: Fragment? = null

        fun getInstance(): Fragment
            = Instance ?: synchronized(this) {
            Instance ?: buildFragment().also { Instance = it }}

        private fun buildFragment(): Fragment
                = foodFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_layout_food, container, false)

    private lateinit var presenter: foodPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = foodPresenter(view.context, this)

        presenter.receiveFoodData()
    }

    private lateinit var adapter: foodAdapter
    private fun setRecycler(arrays: List<String>) {
        adapter = foodAdapter(arrays).also {
            recycler.adapter = it
            recycler.layoutManager = LinearLayoutManager(context)

            it.notifyDataSetChanged()
        }
    }

    /**
     *  Presenter
     */
    override fun successToReceive(item: ItemModel) {
        val arrays = item
            .template.outputs[0].simpleText.text.split("\r\n").toMutableList()
            .apply { this[0] = this[0].split("\n")[this[0].split("\n").size - 1] }

        arrays.removeAt(0)

        setRecycler(arrays.toList())
    }

    override fun failedToReceive() {

    }

}