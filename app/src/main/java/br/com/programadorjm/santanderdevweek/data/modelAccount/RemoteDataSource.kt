package br.com.programadorjm.santanderdevweek.data.modelAccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RemoteDataSource: AccountImplements {
    override fun getAccount(): LiveData<ModelAccount?> {
        val data = MutableLiveData<ModelAccount>()
        //Implementar meio de busca a uma api, usando retrofit por exemplo
        return data
    }
}