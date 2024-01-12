package com.example.lesson_1_month_6.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.lesson_1_month_6.data.Resource
import com.example.lesson_1_month_6.ui.utils.showToast

open class BaseActivity : AppCompatActivity() {

    fun <T> LiveData<Resource<T>>.stateHandler(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit)? = null
    ) {
        observe(this@BaseActivity) { mine ->
            state?.invoke(mine)
            when (mine) {
                is Resource.Error -> {
                    this@BaseActivity.showToast(mine.message!!)
                }

                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    if (mine.data != null) {
                        success(mine.data)
                    }
                }
            }
        }
    }
}