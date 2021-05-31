 package br.com.programadorjm.santanderdevweek.view

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.programadorjm.santanderdevweek.R
import br.com.programadorjm.santanderdevweek.data.modelCard.Card
import br.com.programadorjm.santanderdevweek.viewModel.MainViewModel
import com.google.android.material.appbar.MaterialToolbar

 class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {
     //estancia do MainViewModel
     private lateinit var viewModel:MainViewModel

     private lateinit var toolbar : MaterialToolbar
     private lateinit var cards : Array<String>
     private lateinit var imageExpand : ImageView
     private lateinit var layInfo : LinearLayout
     private lateinit var layButtons : LinearLayout
     private lateinit var spinner: Spinner


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         setupView()
         loadView()
    }

     fun setupView(){
         //inicializando MainViewModel
         viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

         //configurando toolbar
         toolbar = findViewById(R.id.main_toolbar)
         setSupportActionBar(toolbar)
         supportActionBar?.setDisplayShowTitleEnabled(false)
         toolbar.setNavigationOnClickListener { Toast.makeText(this, "Tocou no menu", Toast.LENGTH_SHORT).show() }

         //setando click Spinner
         spinner = findViewById(R.id.spn_card)
         spinner.onItemSelectedListener = this

         //configurando recolhimento CardView
         imageExpand = findViewById(R.id.img_expand)
         layInfo = findViewById(R.id.lay_info)
         layButtons = findViewById(R.id.lay_buttons)
         imageExpand.setOnClickListener(this)
     }

     //carregar dados na view
     fun loadView(){
         viewModel.getAccount().observe(this, Observer { account ->
             if (account != null) {
                 findViewById<TextView>(R.id.tv_user_name).text = getString(R.string.str_user_name, account.name)
                 findViewById<TextView>(R.id.tv_agency).text = getString(R.string.str_agency, account.agency)
                 findViewById<TextView>(R.id.tv_account).text = getString(R.string.str_account, account.accountNumber)
                 findViewById<TextView>(R.id.tv_balance).text = getString(R.string.str_balance_account, account.balance)

                 //setando dados spinner
                 val cards:MutableList<String> = mutableListOf()
                 for (Card in account.Cards){
                     cards.add("Cartão final **** ${Card.cardNumber}")
                 }
                 val arrayAdapter:ArrayAdapter<String> = ArrayAdapter<String>(this,
                     R.layout.item_custom_layout_spinner, cards)
                 arrayAdapter.setDropDownViewResource(R.layout.item_custom_layout_spinner)
                 spinner.adapter = arrayAdapter

                 findViewById<TextView>(R.id.tv_balance_limit).text = getString(R.string.str_balance_limit, account.balance + account.limit)

             }else{Toast.makeText(this, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show()}
         })
     }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         val inflate = menuInflater
         inflate.inflate(R.menu.main_menu, menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when(item.itemId) {
             R.id.notify_item ->{
                 Toast.makeText(this, "Tocou item notificação", Toast.LENGTH_SHORT).show()
                 false
             }
             else -> super.onOptionsItemSelected(item)
         }
     }

     override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}

     override fun onNothingSelected(parent: AdapterView<*>?) {}

     override fun onClick(v: View?) {
         val param = layButtons.layoutParams as ViewGroup.MarginLayoutParams
         if (layInfo.isVisible){
             layInfo.visibility = View.GONE
             imageExpand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
             param.setMargins(20.toPx(),70.toPx(),20.toPx(),0) //setando margens em px
         }else{
             layInfo.visibility = View.VISIBLE
             imageExpand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
             param.setMargins(18.toPx(),20.toPx(),20.toPx(),0)
         }
         layButtons.layoutParams = param
     }

     //Converter dp para px
     private fun Int.toPx():Int{
         val metrics = Resources.getSystem().displayMetrics
         return this * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
     }

 }