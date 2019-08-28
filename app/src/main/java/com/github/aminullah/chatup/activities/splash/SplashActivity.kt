package com.github.aminullah.chatup.activities.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.activities.login_screen.LoginActivity
import com.github.aminullah.chatup.utils.gotoActivityWithFinih

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            this.gotoActivityWithFinih(this, LoginActivity::class.java)
        }, 4000)

    }
}
