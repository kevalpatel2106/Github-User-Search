/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevalpatel2106.sample.di

import android.app.Application
import android.content.Context
import com.kevalpatel2106.sample.utils.BaseApplication
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Keval on 01/06/18.
 * Dagger [Component] that injects dependencies provided by [RootDiModule] into [BaseApplication].
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 * @see RootDiModule
 */
@Singleton
@Component(modules = [RootDiModule::class])
internal interface RootComponent {

    fun inject(application: BaseApplication)

    fun getContext(): Context

    fun getApplication(): Application

    fun geBaseApplication(): BaseApplication

    @Named(RootDiModule.BASE_URL)
    fun getBaseUrl(): String
}
