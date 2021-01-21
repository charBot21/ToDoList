package com.carlostorres.iberica.model.intrfacs

interface LoginListener {

    fun onSuccess(user: String?)
    fun onErrorLogin()
    fun showProgressBar()
    fun hideProgressbar()
}