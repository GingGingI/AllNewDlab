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

class copyRightFragment: Fragment() {
    companion object {
        private var Instance: Fragment? = null

        fun getInstance(): Fragment = Instance ?: synchronized(this) {
            Instance ?: buildFragment().also { Instance = it }
        }

        private fun buildFragment(): Fragment = copyRightFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_layout_copyright, container, false)

    private lateinit var arrays: ArrayList<Pair<String, String>>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrays = arrayListOf(
            Pair("https://github.com/square/retrofit", "retrofit"),
            Pair("https://github.com/square/okhttp", "okhttp3"),
            Pair("https://github.com/airbnb/lottie-android", "lottie-android"))
        setRecycler(arrays)
    }

    private lateinit var adapter: copyRightAdapter
    private fun setRecycler(arrays: List<Pair<String, String>>) {
        adapter = copyRightAdapter(arrays).also {
            recycler.adapter = it
            recycler.layoutManager = LinearLayoutManager(context?.applicationContext)

            it.notifyDataSetChanged()
        }
    }
}