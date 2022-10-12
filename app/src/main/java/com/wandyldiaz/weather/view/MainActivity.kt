package com.wandyldiaz.weather.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.wandyldiaz.weather.R
import com.wandyldiaz.weather.model.domain.data.SearchListResponseData
import com.wandyldiaz.weather.model.domain.data.SearchResponseData
import com.wandyldiaz.weather.view.search.SearchInterfaceOnClick
import com.wandyldiaz.weather.view.search.SearchViewAdapter
import java.net.HttpURLConnection.HTTP_OK

class MainActivity : InitActivity(), SearchInterfaceOnClick {
    private lateinit var searchResult: List<SearchResponseData>
    private lateinit var adapter: SearchViewAdapter
    private lateinit var searchList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemClock.sleep(2000);
        setContentView(R.layout.activity_main)
        setUpList()
    }

    private fun setUpList() {
        this.searchResult = mutableListOf()
        searchList = findViewById(R.id.listSearch)
        adapter = SearchViewAdapter(this)
        searchList.adapter = adapter

        val observer = Observer<SearchListResponseData>(){searchResponse ->
            searchResponse(searchResponse)
        }

        searchViewModel.getResponseSearch().observe(this, observer)
    }

    private fun searchResponse(responseData: SearchListResponseData){
        if (responseData.code!=HTTP_OK){
           Toast.makeText(this, responseData.response, Toast.LENGTH_LONG).show()
            return;
        }
        searchResult=responseData.searchResponseDataList
        adapter.submitList(responseData.searchResponseDataList)

    }
    private fun searchDataInitial() {
        adapter.submitList(searchResult)
    }
    private fun searchData(newQuery: String) {
            searchViewModel.startSearch(newQuery)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView

        searchView.setSearchableInfo(
            sm.getSearchableInfo(componentName)
        )
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrBlank())
                    searchData(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        menuItem.setOnActionExpandListener(object :
            MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                searchDataInitial()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                searchDataInitial()
                return true
            }
        })

        return true
    }

    override fun foreCastClick(name: String) {
        val intent = Intent(this, ForeCastActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }
}
