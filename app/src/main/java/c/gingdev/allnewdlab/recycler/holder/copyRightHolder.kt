package c.gingdev.allnewdlab.recycler.holder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.*
import kotlinx.android.synthetic.main.layout_copyright_items.view.*



class copyRightHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_copyright_items, parent, false)) {


    fun bind(itemUrl: String, item: String) {
        itemView.run {
            content.text = item
            url.text = itemUrl
        }

        itemView.setOnClickListener {
            val clipBoard = parent.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("content", itemUrl)
            clipBoard.primaryClip = clipData

            Toast.makeText(parent.context, "정상적으로 복사되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}