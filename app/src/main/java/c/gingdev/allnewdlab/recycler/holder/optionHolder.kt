package c.gingdev.allnewdlab.recycler.holder

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.*
import kotlinx.android.synthetic.main.layout_option_items.view.*


class optionHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_option_items, parent, false)) {

    fun bind(itemId: Int, item: String) {
        itemView.run {
            content.text = item
        }

        itemView.setOnClickListener {
            when(itemId) {
                grade -> {
                    Intent(parent.context, GradeActivity::class.java)
                        .apply { putExtra("isSetting", true) }
                        .run { parent.context.startActivity(this) }
                }
                createBy -> {

                }
                openSource -> {
                    Intent(parent.context, OptionsDetailActivity::class.java)
                        .run { parent.context.startActivity(this) }
                }
            }
        }
    }
}