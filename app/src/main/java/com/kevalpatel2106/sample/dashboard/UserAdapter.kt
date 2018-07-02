package com.kevalpatel2106.sample.dashboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kevalpatel2106.sample.AppConfig
import com.kevalpatel2106.sample.bin.UserInfo
import com.kevalpatel2106.sample.utils.PageRecyclerViewAdapter

/**
 * Created by Kevalpatel2106 on 21-Jun-18.
 * [RecyclerView.Adapter] to bind the [UserViewHolder] with [forecasts] list.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal class UserAdapter(
        context: Context,
        forecasts: ArrayList<UserInfo>,
        listener: RecyclerViewListener<UserInfo>?
) : PageRecyclerViewAdapter<UserViewHolder, UserInfo>(context, forecasts, listener) {
    override fun bindView(holder: UserViewHolder, item: UserInfo) {
        holder.bind(item)
    }

    override fun prepareViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(context, parent)
    }

    override fun prepareViewType(position: Int): Int {
        return 1
    }

    override fun getPageSize(): Int {
        return AppConfig.PAGE_SIZE
    }
}
