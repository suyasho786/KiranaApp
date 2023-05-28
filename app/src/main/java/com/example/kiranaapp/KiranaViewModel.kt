package com.example.kiranaapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KiranaViewModel(private val repository: KiranaRepository) : ViewModel() {
    fun insert(items : KiranaItems) = GlobalScope.launch {
        repository.insert(items)
    }
    fun delete(items : KiranaItems) = GlobalScope.launch {
        repository.delete(items)
    }
    fun getAllKiranaItems() = repository.getAllItems()
}