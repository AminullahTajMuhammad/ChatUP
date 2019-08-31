package com.github.aminullah.chatup.activities.login_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.utils.gone
import com.github.aminullah.chatup.utils.visible
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupListeners()
    }

    fun setupListeners() {
        btnLogin.setOnClickListener {
            btnLogin.gone()
            loginProgress.visible()

            Handler().postDelayed({
                btnLogin.visible()
                loginProgress.gone()
            }, 3000)
        }

        btnIcon.setOnClickListener {
            btnLogin.gone()
            loginProgress.visible()

            Handler().postDelayed({
                btnLogin.visible()
                loginProgress.gone()
            }, 3000)
        }

        btnText.setOnClickListener {
            btnLogin.gone()
            loginProgress.visible()

            Handler().postDelayed({
                btnLogin.visible()
                loginProgress.gone()
            }, 3000)
        }
    }
}
