package com.mmk.chat.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mmk.chat.BR


/**
 * Created by mirzemehdi on 8/11/20
 */

/**
 * GenericRecyclerViewAdapter represents for example instead of each time creating adapter
 * for RecyclerView adapter for each list, just extend this class passing
 * layoutId, Item model class, and DiffUtilItemCallback and rest will be handled through this class
 * using DataBinding
 */

open class GenericRecyclerViewHolder<T> constructor(
    private val binding: ViewDataBinding,
    private val onClickItem: ((item: T) -> Unit)? = null,
    private val onBinding: ((item: T, binding: ViewDataBinding) -> Unit)? = null
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.apply {
            setVariable(BR.listItem, item)
            onBinding?.invoke(item, binding)
            executePendingBindings()

        }
        itemView.setOnClickListener { onClickItem?.invoke(item) }
    }

    companion object {
        fun <T> from(
            parent: ViewGroup,
            @LayoutRes layoutId: Int,
            onClickItem: ((item: T) -> Unit)?,
            onBinding: ((item: T, binding: ViewDataBinding) -> Unit)?
        ): GenericRecyclerViewHolder<T> {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
            return GenericRecyclerViewHolder(binding, onClickItem, onBinding)
        }
    }
}


open class GenericRecyclerViewAdapter<T : Any>(
    @LayoutRes val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, GenericRecyclerViewHolder<T>>(diffCallback) {

    open var onClickItem: ((item: T) -> Unit)? = null

    //Only class which is child will see this method
    protected open fun onBinding(item: T, binding: ViewDataBinding) = Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericRecyclerViewHolder<T> {

        return GenericRecyclerViewHolder.from(parent, layoutId, onClickItem, ::onBinding)

    }

    override fun onBindViewHolder(holder: GenericRecyclerViewHolder<T>, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }


}






