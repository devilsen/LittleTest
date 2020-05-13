package me.sam.test.jetpack.paging

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_paging_test.*
import me.sam.test.jetpack.R
import me.sam.test.jetpack.paging.model.CatModel

/**
 * desc: paging test
 * date: 2020/05/11
 * @author: dongsen
 * data : https://docs.thecatapi.com/pagination
 */
class PagingTestActivity : RxAppCompatActivity() {

    private lateinit var catViewModel:CatViewModel
    private lateinit var catAdapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_test)

        catAdapter = CatAdapter()
        recyclerView.apply {
            adapter = catAdapter
            layoutManager = LinearLayoutManager(this@PagingTestActivity)
        }
        swipeLayout.isRefreshing = true

        catViewModel = ViewModelProvider(this).get(CatViewModel::class.java)
        catViewModel.catLiveData.observe(this, Observer {
            catAdapter.submitList(it)
            swipeLayout.isRefreshing = false
        })

        swipeLayout.setOnRefreshListener {
            catViewModel.request()
        }
    }

}