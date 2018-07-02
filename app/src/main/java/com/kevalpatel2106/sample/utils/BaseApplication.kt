/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevalpatel2106.sample.utils

import android.app.Application
import android.content.Context
import com.kevalpatel2106.sample.di.RootComponent

/**
 * Created by Keval on 01/06/18.
 * Base [Application] that can be extended in different build variants.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal abstract class BaseApplication : Application() {

    /**
     * Dagger component to provide the root objects of the dependency graph.
     *
     * @see RootComponent
     */
    protected lateinit var rootComponent: RootComponent

    /**
     * Prepare the [RootComponent] that will contain the root of the dependency graph. You can easily
     * mock these objects for tests by providing mock version of [RootComponent]. See android test
     * source for detail.
     */
    protected abstract fun prepareRootComponent(): RootComponent

    /**
     * Inject [RootComponent] into the class that inherits [BaseApplication].
     */
    protected abstract fun injectRootComponent()

    override fun onCreate() {
        super.onCreate()

        //Create app component
        rootComponent = prepareRootComponent()

        //Inject dagger
        injectRootComponent()
    }

    companion object {

        /**
         * Static factory method to get [rootComponent]. This is casts [Application] to [BaseApplication]
         * internally.
         */
        fun getRootComponent(context: Context): RootComponent {
            return (context.applicationContext as BaseApplication).rootComponent
        }
    }
}
