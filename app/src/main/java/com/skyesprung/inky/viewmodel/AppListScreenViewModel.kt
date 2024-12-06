package com.skyesprung.inky.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class AppListScreenViewModel : ViewModel() {
    private var _apps = MutableLiveData<List<ApplicationInfo>>()
    val apps : LiveData<List<ApplicationInfo>>
        get() = _apps
    private var systime = MutableLiveData<LocalDateTime>(LocalDateTime.now())
    val time: LiveData<LocalDateTime> get() = systime
    fun syncTime() {
        systime.value = LocalDateTime.now()
    }
    @SuppressLint("QueryPermissionsNeeded")
    fun loadApps(pm: PackageManager) {
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val acts = pm.queryIntentActivities(mainIntent, 0)
        Log.i("packageCount", acts.size.toString())
        _apps.value = acts.map{ it.activityInfo.packageName }.toSet().toList().map { pm.getApplicationInfo(it, 0) }
    }
}