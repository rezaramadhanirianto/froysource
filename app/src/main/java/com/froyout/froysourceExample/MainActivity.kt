package com.froyout.froysourceExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.froyout.froysourceExample.data.repository.IMainRepository
import com.froyout.froysourceExample.data.repository.MainRepository
import androidx.lifecycle.lifecycleScope
import com.froyout.froysourceExample.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val mainRepository: IMainRepository by lazy { MainRepository() }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvHello.setOnClickListener {
            lifecycleScope.launch {
                mainRepository.getCatFacts().collect { res ->
                    when(res){
                        is Resource.Success -> {
                            println("SUCCESS: ${res.data}")
                        }
                        is Resource.Error -> {
                            println("ERROR: ${res.message}")
                        }
                        is Resource.Loading -> {
                            println("LOADING: .....")
                        }
                    }
                }
            }
        }
    }
}