package com.kevalpatel2106.sample.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.kevalpatel2106.sample.R
import com.kevalpatel2106.sample.bin.UserInfo
import com.kevalpatel2106.sample.di.DaggerAppDiComponent
import com.kevalpatel2106.sample.utils.BaseApplication
import com.kevalpatel2106.sample.utils.DividerItemDecoration
import com.kevalpatel2106.sample.utils.PageRecyclerViewAdapter
import com.kevalpatel2106.sample.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


/**
 * [AppCompatActivity] to display current weather and forecast for the upcoming four days.
 */
class MainActivity : AppCompatActivity(), PageRecyclerViewAdapter.RecyclerViewListener<UserInfo> {

    /**
     * [MainViewModel]
     */
    private lateinit var model: MainViewModel

    @Inject
    internal lateinit var viewModelProvides: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppDiComponent.builder()
                .rootComponent(BaseApplication.getRootComponent(this@MainActivity))
                .build()
                .inject(this@MainActivity)
        model = viewModelProvides.create(MainViewModel::class.java)

        setContentView(R.layout.activity_main)

        // Handle loading
        model.isLoading.observe(this@MainActivity, Observer {
            it?.let {
                if (it && model.users.value!!.isEmpty()) {
                    main_view_flipper.displayedChild = POS_LOADER
                }
            }
        })

        // Handle error
        model.errorLoading.observe(this@MainActivity, Observer {
            it?.let {
                Snackbar.make(main_root_view, it, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry) {
                            model.searchUser(main_search_et.text.trim().toString())
                        }
                        .show()
            }
        })


        // Set the users list
        user_list_rv.layoutManager = LinearLayoutManager(this@MainActivity)
        user_list_rv.adapter = UserAdapter(this@MainActivity, model.users.value!!, this)
        user_list_rv.addItemDecoration(DividerItemDecoration(
                this@MainActivity,
                resources.getDimensionPixelSize(R.dimen.user_row_horizontal_padding)
        ))
        model.users.observe(this@MainActivity, Observer {
            it?.let {
                (user_list_rv.adapter as UserAdapter).onPageLoadComplete()

                main_view_flipper.displayedChild = POS_USER_LIST
                user_list_rv.adapter.notifyDataSetChanged()
            }
        })
        model.noMoreItem.observe(this@MainActivity, Observer {
            it?.let { (user_list_rv.adapter as UserAdapter).hasNextPage = !it }
        })

        // Set the search
        btn_search.setOnClickListener {
            hideKeyboard()
            model.searchUser(main_search_et.text.trim().toString())
        }
        main_search_et.addTextChangedListener(object : TextWatcher {
            private var message: Runnable? = null

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }

            override fun onTextChanged(sequance: CharSequence, p1: Int, p2: Int, p3: Int) {
                message?.let { Handler().removeCallbacks(it) }
                message = Runnable { model.searchUser(sequance.trim().toString()) }
                Handler().postDelayed(message, 500 /* ms */)
            }

        })
    }

    override fun onPageComplete(pos: Int) {
        model.loadNextPage()
    }

    companion object {
        // View flipper item positions.
        private const val POS_LOADER = 0
        private const val POS_USER_LIST = 1
    }
}
