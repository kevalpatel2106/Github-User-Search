/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevalpatel2106.sample.repository

import com.kevalpatel2106.sample.bin.UserInfo
import com.kevalpatel2106.sample.repository.network.Endpoint
import com.kevalpatel2106.sample.repository.network.Network
import io.reactivex.Single

/**
 * Created by Keval on 01/06/18.
 * Concrete implementation of the [Repository]. This class provides required data to other layer
 * of the application (such as views and view models) by managing caches and network requests.
 *
 * @param network [Network] for  saving data into network
 * @see Repository
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal class RepositoryImpl(private val network: Network) : Repository {

    override fun searchUser(query: String, page: Int): Single<ArrayList<UserInfo>> {
        return network
                .getRetrofitClient()
                .create(Endpoint::class.java)
                .searchUser(page = page.toString(), sort = "followers", order = "desc", query = query)
                .map {
                    val users = ArrayList<UserInfo>()
                    it.items.forEach { users.add(it.toUser()) }
                    return@map users
                }
    }

}
