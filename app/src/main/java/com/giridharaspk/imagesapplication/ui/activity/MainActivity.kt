package com.giridharaspk.imagesapplication.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giridharaspk.imagesapplication.data.api.ApiResult
import com.giridharaspk.imagesapplication.data.model.ImageDetails
import com.giridharaspk.imagesapplication.data.repository.DataRepository
import com.giridharaspk.imagesapplication.databinding.ActivityMainBinding
import com.giridharaspk.imagesapplication.ui.adapter.ImagesAdapter
import com.giridharaspk.imagesapplication.ui.viewmodel.MainViewModel
import com.giridharaspk.imagesapplication.ui.viewmodel.ViewModelProviderFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var imageDetailsAdapter: ImagesAdapter
    private val context = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideProgress()

        setupViewModel()
        setupSwipeToRefresh()
        setAdapter()
        setObservers()
        viewModel.getImages()
    }

    private fun setupSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getImages()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProviderFactory(
                MainViewModel(
                    app = application,
                    DataRepository,
                )
            )
        ).get(MainViewModel::class.java)

    }

    private fun setAdapter() {
        imageDetailsAdapter = ImagesAdapter(context)
        imageDetailsAdapter.setImagesList(ArrayList())
        binding.rvImages.apply {
            adapter = imageDetailsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setObservers() {
        viewModel.imagesList.observe(this, Observer {
            when (it) {
                is ApiResult.Loading -> {
                    Timber.d("Loading")
                    showProgress()
                }
                is ApiResult.Success -> {
                    Timber.d("Success")
                    hideProgress()
                    //handle success response
                    it.data?.let { resp ->
                        val loadingList = ArrayList<ImageDetails>()
                        resp.rows?.let { rows ->
                            rows.forEach { data ->
                                if (data == null || (data.title == null && data.description == null && data.imageHref == null)) {
                                    //ignore incorrect data
                                } else {
                                    loadingList.add(data)
                                }
                            }
                        }
                        imageDetailsAdapter.setImagesList(loadingList)
                        supportActionBar?.title = resp.title
                    }
                }
                is ApiResult.Failure -> {
                    Timber.d("Failure")
                    hideProgress()
                    val msg = it.message ?: "Something went wrong"
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                }
                is ApiResult.Error -> {
                    Timber.d("Error")
                    hideProgress()
                    it.message?.let { message ->
                        Timber.e("An error occurred $message")
                    }
                    it.t?.let {
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                        Timber.e(it)
                    }
                }
                is ApiResult.NetworkError -> {
                    hideProgress()
                    Timber.e("Network error")
                    Toast.makeText(this, "Please Check your network", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showProgress() {
        binding.shimmerFrameLayout.startShimmer()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.swipeToRefresh.isRefreshing = false
    }

}