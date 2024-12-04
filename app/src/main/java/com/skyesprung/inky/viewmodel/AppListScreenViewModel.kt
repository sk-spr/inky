package com.skyesprung.inky.viewmodel

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppListScreenViewModel : ViewModel() {
    private var _apps = MutableLiveData<List<ApplicationInfo>>()
    val apps : LiveData<List<ApplicationInfo>>
        get() = _apps

    @SuppressLint("QueryPermissionsNeeded")
    fun loadApps(pm: PackageManager) {
        _apps.value = pm.getInstalledApplications(PackageManager.GET_META_DATA)
    }
}