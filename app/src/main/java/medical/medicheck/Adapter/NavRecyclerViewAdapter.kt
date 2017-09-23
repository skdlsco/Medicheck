package medical.medicheck.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.navigation_item.view.*
import medical.medicheck.Models.NavItem
import medical.medicheck.R

/**
 * Created by eka on 2017. 9. 23..
 */
class NavRecyclerViewAdapter(private val context: Context, private val items: ArrayList<NavItem>) : RecyclerView.Adapter<NavRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (items[position].isSelected) {
            holder?.itemView?.image?.setImageResource(items[position].s_src)
            holder?.itemView?.setBackgroundColor(context.resources.getColor(R.color.colorRed))
            holder?.itemView?.text?.setTextColor(context.resources.getColor(R.color.colorWhite))
            holder?.itemView?.text?.text = items[position].text
        } else {
            holder?.itemView?.image?.setImageResource(items[position].src)
            holder?.itemView?.setBackgroundColor(context.resources.getColor(R.color.colorWhite))
            holder?.itemView?.text?.setTextColor(context.resources.getColor(R.color.colorRed))
            holder?.itemView?.text?.text = items[position].text
        }
        holder?.itemView?.setOnClickListener {
            itemClick?.onItemClick(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.navigation_item, null)
        return ViewHolder(view)
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onItemClick(view: View?, position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}