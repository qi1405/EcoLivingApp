package com.example.dimi.ecocare

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mancj.materialsearchbar.MaterialSearchBar


class ListClients : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_clients)
        var ad: SimpleAdapter? = null
        val lstv = findViewById<View>(R.id.listview1) as ListView
        var MyDataList: List<Map<String?, String?>?>? = null
        val MyData = ListItem()
        MyDataList = MyData.getlist()
        val Fromw = arrayOf("tvCustomerID", "tvName", "tvSurname", "tvCity", "tvAddress", "tvCredit", "tvDebit")
        val Tow = intArrayOf(R.id.tvCustomerID, R.id.tvName, R.id.tvSurname, R.id.tvCity, R.id.tvAddress, R.id.tvCredit, R.id.tvDebit)
        ad = SimpleAdapter(this@ListClients, MyDataList, R.layout.listlayouttemplate, Fromw, Tow)
        lstv.adapter = ad

//REFERENCE MATERIALSEARCHBAR
        val searchBar = findViewById(R.id.searchBar) as MaterialSearchBar
        searchBar.setHint("Kërko...")
        searchBar.setSpeechMode(true)

//SEARCHBAR TEXT CHANGE LISTENER
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                ad.getFilter().filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
                
            }

        }

        )


    }

/*
    var ad: SimpleAdapter? = null
    fun GetList(v: View?) {
        val lstv = findViewById<View>(R.id.listview1) as ListView
        var MyDataList: List<Map<String?, String?>?>? = null
        val MyData = ListItem()
        MyDataList = MyData.getlist()
        val Fromw = arrayOf("tvCustomerID", "tvName", "tvSurname", "tvCity", "tvAddress", "tvCredit", "tvDebit")
        val Tow = intArrayOf(R.id.tvCustomerID, R.id.tvName, R.id.tvSurname, R.id.tvCity, R.id.tvAddress, R.id.tvCredit, R.id.tvDebit)
        ad = SimpleAdapter(this@ListClients, MyDataList, R.layout.listlayouttemplate, Fromw, Tow)
        lstv.adapter = ad
    }
*/


   
}