package br.com.programadorjm.santanderdevweek.data.modelAccount

import android.accounts.Account
import androidx.lifecycle.LiveData

//Interface onde tanto repositorio local como o remoto deve implemetar para retornar o objeto
interface AccountImplements {
    fun getAccount():LiveData<ModelAccount?>
}