package com.giridharaspk.imagesapplication.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giridharaspk.imagesapplication.data.repository.DataRepository
import com.giridharaspk.imagesapplication.databinding.ActivityMainBinding
import com.giridharaspk.imagesapplication.ui.adapter.ImagesAdapter
import com.giridharaspk.imagesapplication.ui.viewmodel.MainViewModel
import com.giridharaspk.imagesapplication.ui.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var imageDetailsAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupSwipeToRefresh()
        setAdapter()
        setObservers()
    }

    private fun setupSwipeToRefresh() {
        TODO("Not yet implemented")
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
        imageDetailsAdapter = ImagesAdapter()
        imageDetailsAdapter.setImagesList(ArrayList())
        binding.rvImages.apply {
            adapter = imageDetailsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setObservers() {
        TODO("Not yet implemented")
    }

    private fun showProgress() {
        binding.shimmerFrameLayout.startShimmer()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
    }

}