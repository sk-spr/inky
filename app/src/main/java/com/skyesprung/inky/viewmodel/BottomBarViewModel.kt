package com.skyesprung.inky.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomBarViewModel : ViewModel() {
    private var _currentPage = MutableLiveData(0)
    val currentPage: LiveData<Int> get() = _currentPage
    fun changePageSelection(nextPage: Int) {
        _currentPage.value = nextPage
    }
}