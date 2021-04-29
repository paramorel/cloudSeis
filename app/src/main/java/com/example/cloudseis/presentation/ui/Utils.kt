package com.example.cloudseis.presentation.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.example.cloudseis.network.Resource
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.login.LoginFragment
import com.example.cloudseis.presentation.ui.login.RegisterFragment


fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> requireView().snackbar(
            "Please check your internet connection",
            retry
        )
//        failure.errorCode == 401 -> {
//            if (this is LoginFragment) {
//                requireView().snackbar("You've entered incorrect email or password")
//            }
//        }
        failure.errorCode == 400 -> {
            if (this is RegisterFragment) {
                requireView().snackbar("Пользователь с таким логином уже существует")
            }
        }
//        failure.errorCode == 404 -> {
//            if (this is LoginFragment) {
//                requireView().snackbar("Пользователь с таким логином не найден")
//            }
//        }
        failure.errorCode == 403 -> {
            if (this is LoginFragment) {
                requireView().snackbar("Вы ввели неверный логин или пароль")
            }
        }

        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}

