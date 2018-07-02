package com.kevalpatel2106.sample.dashboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevalpatel2106.sample.R
import com.kevalpatel2106.sample.bin.UserInfo
import com.kevalpatel2106.sample.utils.PageRecyclerViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_user.view.*

/**
 * Created by Kevalpatel2106 on 21-Jun-18.
 * [RecyclerView.ViewHolder] for the row that displays the weather forecast fro upcoming days.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal class UserViewHolder(itemView: View) : PageRecyclerViewAdapter.PageViewHolder(itemView) {
    companion object {

        /**
         * Create the new instance of [UserViewHolder].
         */
        fun create(context: Context, parent: ViewGroup): UserViewHolder {
            return UserViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.row_user, parent, false))
        }
    }

    /**
     * Bind the [userInfo] with the views of the [UserViewHolder].
     */
    fun bind(userInfo: UserInfo) {
        itemView.user_row_name_tv.text = userInfo.username
        Picasso.get().load(userInfo.avatarUrl).into(itemView.user_row_avatar_iv)
    }
}

