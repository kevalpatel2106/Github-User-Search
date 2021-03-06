/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevalpatel2106.sample.di

import com.kevalpatel2106.sample.repository.RepoDiModule
import com.kevalpatel2106.sample.utils.ApplicationScope
import dagger.Component

/**
 * Created by Kevalpatel2106 on 04-Jun-18.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
@ApplicationScope
@Component(
        dependencies = [MockRootComponent::class],
        modules = [RepoDiModule::class]
)
interface TestAppComponent {

    fun inject(mainActivityTest: MainActivityTest)
}
