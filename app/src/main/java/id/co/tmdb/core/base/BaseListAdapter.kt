package id.co.tmdb.core.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import id.co.tmdb.BR

class BaseListAdapter(
    @LayoutRes
    var layoutId: Int = 0
) : RecyclerView.Adapter<BaseListAdapter.Holder>() {
    var items: List<*>? = null
    var onItemClick = fun(_: View, _: Int): Unit = Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            layoutId,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items?.get(position))
    }

    inner class Holder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ob: Any?) {
            binding.setVariable(BR.model, ob)
            binding.setVariable(BR.handler, this)
            binding.setVariable(BR.position, adapterPosition)
            binding.executePendingBindings()
        }

        fun onClick(view: View, position: Int) {
            onItemClick(view, position)
        }
    }
}