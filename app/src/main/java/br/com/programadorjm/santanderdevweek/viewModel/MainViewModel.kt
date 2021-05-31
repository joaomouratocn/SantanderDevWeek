package br.com.programadorjm.santanderdevweek.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.programadorjm.santanderdevweek.data.modelAccount.AccountImplements
import br.com.programadorjm.santanderdevweek.data.modelAccount.LocalDatasource
import br.com.programadorjm.santanderdevweek.data.modelAccount.ModelAccount

class MainViewModel(private val accountImpl: AccountImplements = LocalDatasource()) : ViewModel() {

    fun getAccount():LiveData<ModelAccount?>{
        return accountImpl.getAccount()
    }
}