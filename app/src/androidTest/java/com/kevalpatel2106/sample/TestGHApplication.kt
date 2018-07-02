/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kevalpatel2106.sample

import com.kevalpatel2106.testutils.MockServerManager
import com.kevalpatel2106.sample.di.DaggerMockRootComponent
import com.kevalpatel2106.sample.di.MockRootComponent
import com.kevalpatel2106.sample.di.MockRootDiModule
import com.kevalpatel2106.sample.di.RootComponent
import com.kevalpatel2106.sample.utils.BaseApplication
import java.io.IOException

/**
 * Created by Keval on 01/06/18.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal class TestGHApplication : BaseApplication() {

    var mockServerManager = MockServerManager()

    var baseUrl: String = ""

    override fun onCreate() {
        super.onCreate()

        // Start the mock server
        val thread = object : Thread() {
            override fun run() {
                try {
                    mockServerManager.startMockWebServer()
                    // Pass the mock web server url
                    baseUrl = mockServerManager.getBaseUrl()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        thread.start()

        // Wait for the server to start
        Thread.sleep(1000L)
    }

    /**
     * Prepare the [RootComponent] that will contain the root of the dependency graph. You can easily
     * mock these objects for tests by providing mock version of [RootComponent]. See android test
     * source for detail.
     */
    override fun prepareRootComponent(): RootComponent {
        return DaggerMockRootComponent.builder()
                .mockRootDiModule(MockRootDiModule(this@TestGHApplication))
                .build()
    }

    override fun injectRootComponent() {
        (rootComponent as MockRootComponent).inject(this@TestGHApplication)
    }
}
