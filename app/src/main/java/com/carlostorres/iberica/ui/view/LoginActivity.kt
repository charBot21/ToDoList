package com.carlostorres.iberica.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.carlostorres.iberica.R
import com.carlostorres.iberica.data.local.prefrns.PrefernsProvidr
import com.carlostorres.iberica.databinding.ActivityLoginBinding
import com.carlostorres.iberica.model.intrfacs.LoginListener
import com.carlostorres.iberica.ui.viewmodel.LoginVM
import com.carlostorres.iberica.utils.view.hide
import com.carlostorres.iberica.utils.view.show
import com.carlostorres.iberica.utils.view.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , LoginListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewmodel: LoginVM

    private lateinit var preferences: PrefernsProvidr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Databinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewmodel = ViewModelProvider(this).get(LoginVM::class.java)
        binding.login = viewmodel
        viewmodel.loginListener = this

        preferences = PrefernsProvidr(applicationContext)

        validateActiveSession()
    }

    fun validateActiveSession() {

        if ( preferences.getBoolean("success") ) {
            val successIntent = Intent(this, HomeActivity::class.java)
            startActivity(successIntent)
        }

    }


    override fun onSuccess(user: String?) {
        val successIntent = Intent(this, HomeActivity::class.java)


        preferences.putString("user", user!!)
        preferences.putBoolean("success", true)

        // Cambio de vista
        startActivity(successIntent)
        finish()
    }

    override fun onErrorLogin() {
        toast(getString(R.string.invalid_credentials))
    }

    override fun showProgressBar() {
        progressbarLogin.show()
    }

    override fun hideProgressbar() {
        progressbarLogin.hide()
    }
}