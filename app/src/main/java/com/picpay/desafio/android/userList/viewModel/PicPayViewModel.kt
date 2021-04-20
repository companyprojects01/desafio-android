package com.picpay.desafio.android.userList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.userList.model.Contact
import com.picpay.desafio.android.userList.model.repository.local.LocalRepository
import com.picpay.desafio.android.userList.model.repository.remote.RemoteRepository
import com.picpay.desafio.android.userList.model.states.StatesAPI
import com.picpay.desafio.android.utils.extensions.runTaskOnBackground

internal class PicPayViewModel(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _successLiveData = MutableLiveData<List<Contact>>()
    val successLiveData: LiveData<List<Contact>> = _successLiveData

    private val _errorLiveData = MutableLiveData<StatesAPI>()
    val errorLiveData: LiveData<StatesAPI> = _errorLiveData

    @Suppress("UNCHECKED_CAST")
    fun getUserList() {
        runTaskOnBackground {
            when (val value = remoteRepository.getUserList()) {
                is StatesAPI.Success<*> -> {
                    val list = value.data as List<Contact>
                    dropContactsTable(list)
                    cacheList(list)
                    _successLiveData.postValue(list)
                }
                is StatesAPI.Error -> {
                    when (value.error) {
                        is StatesAPI.ClientErrorViewState -> {
                            _errorLiveData.postValue(StatesAPI.ClientErrorViewState)
                        }
                        is StatesAPI.ServerErrorViewState -> {
                            _errorLiveData.postValue(StatesAPI.ServerErrorViewState)
                        }
                        else -> {
                            _errorLiveData.postValue(StatesAPI.Exception)
                        }
                    }
                }
            }
        }
    }

    private fun dropContactsTable(contacts: List<Contact>) {
        runTaskOnBackground { localRepository.dropContactsTable(contacts) }
    }

    private fun cacheList(contacts: List<Contact>) {
        runTaskOnBackground {
            localRepository.saveAllContacts(contacts)
        }
    }

    fun getListFromCache() {
        runTaskOnBackground {
            _successLiveData.postValue(localRepository.fetchCachedContacts())
        }
    }

    fun hasASuccessfulCallAlreadyBeenMade(): Boolean {
        successLiveData.value?.let {
            return true
        } ?: run {
            return false
        }
    }
}
