package c.gingdev.allnewdlab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.constracts.calendarFragmentConstract
import c.gingdev.allnewdlab.models.ItemModel
import c.gingdev.allnewdlab.presenters.calendarPresenter
import c.gingdev.allnewdlab.recycler.adapter.calendarAdapter
import kotlinx.android.synthetic.main.fragment_layout_calendar.*


class calendarFragment: Fragment(),
    baseInterface by baseFragment(),
    calendarFragmentConstract.view{
    companion object {
        private var Instance: Fragment? = null

        fun getInstance(): Fragment
                = Instance ?: synchronized(this) {
            Instance ?: buildFragment().also { Instance = it }}

        private fun buildFragment(): Fragment
                = calendarFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_layout_calendar, container, false)

    private lateinit var presenter: calendarPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnim(view.context, loadingLottie)

        startLoading()
        presenter = calendarPresenter(view.context, this)

        presenter.receiveCalendarData()
    }

    private lateinit var adapter: calendarAdapter
    private fun setRecycler(arrays: List<String>) {
        adapter = calendarAdapter(arrays).also {
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
        val arrays = item
            .template.outputs[0].simpleText.text.split("\n").toMutableList()
            .apply { this[0] = this[0].split("\n")[this[0].split("\n").size - 1] }
            .also {
                it.removeAt(0)
                it.removeAt(0)
            }

        setRecycler(arrays)
    }

    override fun failedToReceive() {
        finishLoading()
        Toast.makeText(context, "데이터 받아오기 실패!", Toast.LENGTH_SHORT).show()
    }
}