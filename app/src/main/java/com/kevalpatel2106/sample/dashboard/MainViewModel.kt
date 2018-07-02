package com.kevalpatel2106.sample.dashboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kevalpatel2106.sample.AppConfig
import com.kevalpatel2106.sample.R
import com.kevalpatel2106.sample.bin.UserInfo
import com.kevalpatel2106.sample.repository.Repository
import com.kevalpatel2106.sample.utils.BaseApplication
import com.kevalpatel2106.sample.utils.SingleLiveEvent
import com.kevalpatel2106.sample.utils.recall
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Kevalpatel2106 on 21-Jun-18.
 * View model for [MainActivity].
 *
 * @property application [BaseApplication]
 * @property repository [Repository] for loading the forecast data.
 * @author <a href="https://github.com/kevalpatel2106">kevalpatel2106</a>
 */
internal class MainViewModel @Inject constructor(
        private val application: BaseApplication,
        private val repository: Repository
) : ViewModel() {
    private var searchDisposable: Disposable? = null

    /**
     * [MutableLiveData] for the [ArrayList] of [UserInfo].
     */
    internal val users = MutableLiveData<ArrayList<UserInfo>>()

    /**
     * [SingleLiveEvent] that contains the error message to display in case [repository] fails  to
     * load forecast data.
     */
    internal val errorLoading = SingleLiveEvent<String>()

    /**
     * [MutableLiveData] to set true when the [repository] is loading the forecast detail else it
     * will be false.
     */
    internal val isLoading = MutableLiveData<Boolean>()

    internal val noMoreItem = MutableLiveData<Boolean>()

    internal var page: Int = 1

    internal var query: String? = null

    init {
        users.value = ArrayList()
        isLoading.value = false
        noMoreItem.value = false
    }

    internal fun loadNextPage() {
        searchUser(query, page + 1)
    }

    internal fun searchUser(query: String?, page: Int = 1) {
        if (query.isNullOrBlank()) return

        searchDisposable?.dispose()

        this.query = query
        this.page = page

        searchDisposable = repository.searchUser(query!!, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.value = true }
                .doAfterTerminate { isLoading.value = false }
                .subscribe({

                    noMoreItem.value = it.size < AppConfig.PAGE_SIZE

                    // Update future forecast info
                    if (page == 1) users.value?.clear()
                    users.value?.addAll(it)
                    users.recall()
                }, {
                    noMoreItem.postValue(false)
                    it.printStackTrace()
                    errorLoading.value = application.getString(R.string.error_message)
                })
    }
}
