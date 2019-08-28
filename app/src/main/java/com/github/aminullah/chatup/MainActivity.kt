package com.github.aminullah.chatup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.github.aminullah.chatup.activities.login_screen.LoginActivity
import com.github.aminullah.chatup.utils.gotoActivityWithFinih

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
