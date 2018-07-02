/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevalpatel2106.sample

import com.kevalpatel2106.sample.di.DaggerRootComponent
import com.kevalpatel2106.sample.di.RootComponent
import com.kevalpatel2106.sample.di.RootDiModule
import com.kevalpatel2106.sample.utils.BaseApplication

/**
 * Created by Keval on 01/06/18.
 * [BaseApplication] for the app.
 *
 * @see BaseApplication
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal class GHApplication : BaseApplication() {

    override fun prepareRootComponent(): RootComponent {
        return DaggerRootComponent.builder()
                .rootDiModule(RootDiModule(this@GHApplication))
                .build()
    }

    override fun injectRootComponent() {
        rootComponent.inject(this@GHApplication)
    }
}
