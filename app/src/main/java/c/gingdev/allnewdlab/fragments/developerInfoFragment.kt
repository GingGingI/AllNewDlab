package c.gingdev.allnewdlab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.recycler.adapter.copyRightAdapter
import kotlinx.android.synthetic.main.fragment_layout_copyright.*

class developerInfoFragment: Fragment() {
    companion object {
        private var Instance: Fragment? = null

        fun getInstance(): Fragment = Instance ?: synchronized(this) {
            Instance ?: buildFragment().also { Instance = it }
        }

        private fun buildFragment(): Fragment = developerInfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_layout_copyright, container, false)

    private lateinit var arrays: ArrayList<Pair<String, String>>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrays = arrayListOf(
            Pair(resources.getString(R.string.developer_1_mail), resources.getString(R.string.developer_1)),
            Pair(resources.getString(R.string.developer_2_mail), resources.getString(R.string.developer_2)))
        setRecycler(arrays)
    }

    private lateinit var adapter: copyRightAdapter
    private fun setRecycler(arrays: List<Pair<String, String>>) {
        adapter = copyRightAdapter(arrays).also {
            recycler.adapter = it
            recycler.layoutManager = LinearLayoutManager(context)

            it.notifyDataSetChanged()
        }
    }
}