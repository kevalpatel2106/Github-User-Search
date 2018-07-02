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
import com.kevalpatel2106.sample.BuildConfig
import com.kevalpatel2106.sample.utils.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Keval on 01/06/18.
 * Dagger [Module] to provide application, database and network base url dependencies. In the test
 * environment you can mock this dependencies and inject mocks into the application.
 *
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
@Module
internal class RootDiModule(private val application: Application) {

    companion object {
        const val BASE_URL = "base_url"
    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideBaseApplication(): BaseApplication {
        return application as BaseApplication
    }

    /**
     * Base url of the API endpoints.
     */
    @Singleton
    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}
