package com.example.kiranaapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class KiranaViewModelFactory(private val repository: KiranaRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KiranaViewModel(repository) as T
    }
}