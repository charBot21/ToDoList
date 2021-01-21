package com.carlostorres.iberica.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.carlostorres.iberica.model.intrfacs.LoginListener


class LoginVM(application: Application): AndroidViewModel(application) {

    var loginListener: LoginListener?= null

    val userId = ObservableField<String?>("")
    val userPassword = ObservableField<String?>("")


    fun validateUserInputs(view: View) {
        loginListener?.showProgressBar()

        if ( !userId.get().isNullOrEmpty() && !userPassword.get().isNullOrEmpty() ) {
            loginListener?.onSuccess(userId.get())
        } else {
            loginListener?.onErrorLogin()
        }
        loginListener?.hideProgressbar()

    }
}