package br.com.programadorjm.santanderdevweek.data.modelAccount

import br.com.programadorjm.santanderdevweek.data.modelCard.Card

data class ModelAccount(
    val name:String,
    val agency:String,
    val accountNumber:String,
    val balance: Double,
    val limit: Double,
    val Cards:List<Card>
){}