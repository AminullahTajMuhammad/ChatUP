package com.github.aminullah.chatup.activities.porfile_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.utils.setActionBarColor
import com.github.aminullah.chatup.utils.setActionBarText
import com.github.aminullah.chatup.utils.setBackButton

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Actionbar Buttons
        this.setActionBarColor(R.color.colorApp)
        this.setActionBarText("Profile")
        this.setBackButton()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
