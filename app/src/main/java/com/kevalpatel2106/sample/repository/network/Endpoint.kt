/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevalpatel2106.sample.repository.network

import com.kevalpatel2106.sample.AppConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Keval on 01/06/18.
 * List of all the server endpoints.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal interface Endpoint {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/users")
    fun searchUser(
            @Query("page") page: String,
            @Query("per_page") perPage: Int = AppConfig.PAGE_SIZE,
            @Query("q") query: String,
            @Query("sort") sort: String,
            @Query("order") order: String
    ): Single<SearchResponse>
}
