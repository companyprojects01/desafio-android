package com.picpay.desafio.android.userList.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.userList.model.Contact
import com.picpay.desafio.android.userList.model.states.StatesAPI
import com.picpay.desafio.android.userList.viewModel.PicPayViewModel
import com.picpay.desafio.android.utils.connectionChecking.ConnectionChecker
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    private val viewModel by viewModel<PicPayViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setupView()
        startObservingSuccessState()
        startObservingErrorState()
        callAPI()
    }

    private fun initViews() {
        recyclerView.adapter = adapter
        userListProgressBar.visibility = View.VISIBLE
    }

    private fun setupView() {
        contactSwipeRefreshLayout.setOnRefreshListener {
            callAPI(true)
        }
    }

    private fun callAPI(shouldRefresh: Boolean = false) {
        if (shouldRefresh) {
            viewModel.getUserList()
        } else {
            if (!viewModel.hasASuccessfulCallAlreadyBeenMade()) {
                if (ConnectionChecker.handleInternetConnectionAvailability(
                        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    )
                ) {
                    viewModel.getUserList()
                } else {
                    viewModel.getListFromCache()
                    showErrorToast(R.string.internet_connection_error)
                }
            }
        }
    }

    private fun startObservingSuccessState() {
        viewModel.successLiveData.observe(this, {
            handleSuccess(it)
        })
    }

    private fun startObservingErrorState() {
        viewModel.errorLiveData.observe(this, {
            handleError(it)
        })
    }

    private fun handleSuccess(result: List<Contact>) {
        hideLoadingIndicators()
        adapter.users = result
    }

    private fun handleError(error: StatesAPI) {
        val errorMessage = if (error is StatesAPI.ServerErrorViewState) {
            R.string.server_error
        } else {
            R.string.client_error
        }
        hideLoadingIndicators()
        showErrorToast(errorMessage)
    }

    private fun showErrorToast(@StringRes errorMessage: Int) {
        Toast.makeText(applicationContext, getString(errorMessage), Toast.LENGTH_LONG).show()
    }

    private fun hideLoadingIndicators() {
        userListProgressBar.visibility = View.GONE
        contactSwipeRefreshLayout.isRefreshing = false
    }
}
