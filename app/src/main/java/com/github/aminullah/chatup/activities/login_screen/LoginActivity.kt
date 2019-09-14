package com.github.aminullah.chatup.activities.login_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.activities.users_screen.UserActivity
import com.github.aminullah.chatup.utils.gone
import com.github.aminullah.chatup.utils.gotoActivityWithFinih
import com.github.aminullah.chatup.utils.visible
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.Views {

    lateinit var presenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)

    }

    override fun setupViews() {}


    override fun setupListeners() {
        btnLogin.setOnClickListener {
            btnLogin.gone()
            loginProgress.visible()

            val uid = edtEmail.text.toString()

            if(uid.isEmpty()) {
                edtEmail.error = "Please fill this field"
                btnLogin.visible()
                loginProgress.gone()
                return@setOnClickListener
            }

            presenter.login(uid)
        }
    }

    override fun gotoUsersActivity() {
        this.gotoActivityWithFinih(this, UserActivity::class.java)
    }

    override fun getApiKey(): String {
        return getString(R.string.api_key)
    }
}
