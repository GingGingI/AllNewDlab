package c.gingdev.allnewdlab.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.constracts.scheduleFragmentConstract
import c.gingdev.allnewdlab.models.ItemModel
import c.gingdev.allnewdlab.presenters.schedulePresenter
import c.gingdev.allnewdlab.recycler.adapter.scheduleAdapter
import kotlinx.android.synthetic.main.fragment_layout_schedule.*

class scheduleFragment: Fragment(),
    baseInterface by baseFragment(),
    scheduleFragmentConstract.view {
    companion object {
        private var Instance: Fragment? = null

        fun getInstance(): Fragment
                = Instance ?: synchronized(this) {
            Instance ?: buildFragment().also { Instance = it }}

        private fun buildFragment(): Fragment
                = scheduleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_layout_schedule, container, false)

    private lateinit var presenter: schedulePresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnim(view.context, loadingLottie)

        startLoading()
        presenter = schedulePresenter(view.context, this)

        presenter.receiveScheduleData()
    }

    private lateinit var adapter: scheduleAdapter
    private fun setRecycler(arrays: List<String>) {
        adapter = scheduleAdapter(arrays).also {
            recycler.adapter = it
            recycler.layoutManager = LinearLayoutManager(context)

            finishLoading()
            it.notifyDataSetChanged()
        }
    }

    /**
     *  Presenter
     */
    override fun successToReceive(item: ItemModel) {
        if (!item.template.outputs[0].simpleText.text.contains("값없음")) {
            val arrays = item
                .template.outputs[0].simpleText.text.split("\n").toMutableList()
                .also {
                    it.removeAt(0)
                    it.removeAt(0)
                }

            setRecycler(arrays.toList())
        } else {
            failedToReceive()
        }
    }

    override fun failedToReceive() {
        finishLoading()
        onFailedView.visibility = View.VISIBLE

        restartBtn.setOnClickListener {
            onFailedView.visibility = View.GONE

            startLoading()
            presenter.receiveScheduleData()
        }
    }

}