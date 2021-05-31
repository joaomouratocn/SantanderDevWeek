package br.com.programadorjm.santanderdevweek.data.modelAccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.programadorjm.santanderdevweek.data.modelCard.Card

class LocalDatasource: AccountImplements {
    override fun getAccount(): LiveData<ModelAccount?> {
        val data = MutableLiveData<ModelAccount?>()
        data.value = fakeAccount()
        return data
    }

    private fun fakeAccount():ModelAccount{
        val listCard:MutableList<Card> = mutableListOf()
        for (i in 1..3){
            val card = Card("003${i}", "07/05/2030")
            listCard.add(card)
        }
        return ModelAccount(
            "João Mourato",
            "1234-5",
            "678901-2",
            1250.00,
            1000.00,
            listCard
        )
    }
}