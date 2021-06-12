package com.giridharaspk.imagesapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.giridharaspk.imagesapplication.data.repository.DataRepository
import com.giridharaspk.imagesapplication.databinding.ActivityMainBinding
import com.giridharaspk.imagesapplication.ui.viewmodel.MainViewModel
import com.giridharaspk.imagesapplication.ui.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

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
        TODO("Not yet implemented")
    }

    private fun setObservers() {
        TODO("Not yet implemented")
    }

    private fun showProgress() {
        TODO("Not yet implemented")
    }

    private fun hideProgress() {
        TODO("Not yet implemented")
    }

}