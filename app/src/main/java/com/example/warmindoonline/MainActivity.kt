package com.example.warmindoonline

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.example.warmindoonline.databinding.ActivityDashboardBinding
import com.example.warmindoonline.databinding.ActivityMain2Binding
import com.example.warmindoonline.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var account: Auth0
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ActivityDashboardBinding
    private lateinit var binding3: ActivityMain2Binding
    private var cachedCredentials: Credentials? = null
    private var cachedUserProfile: UserProfile? = null
    lateinit var buttonFinger : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Set Up akun menggunakan Auth0
        account = Auth0(
            getString(R.string.com_auth0_client_id),
            getString(R.string.com_auth0_domain)
        )

        // Bind the button click with the login action
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener { loginWithBrowser() }
        binding.buttonFinger.setOnClickListener{fingerprint()}

        binding2 = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding3 = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding3.btnAuthenticate
    }

//    Inisisasi Fungsi Fingerprint
    private fun fingerprint(){
        buttonFinger = findViewById(R.id.buttonFinger)
        buttonFinger.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

//    Inisiasi Login menggunakan Browser dengan Authen menggunakan Auth0
    private fun loginWithBrowser() {

        val intent = Intent(this, Dashboard::class.java)
        // Setup WebAuthProvider, menggunakan custom skema dan scope.
        WebAuthProvider.login(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .withScope("openid profile email read:current_user update:current_user_metadata")
            .withAudience("https://${getString(R.string.com_auth0_domain)}/api/v2/")

            // Meluncurkan Authentikasi menggunakan perintah callback, nantinya hasil akan diterima.
            .start(this, object : Callback<Credentials, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    showSnackBar("Failure: ${error.getCode()}")
                }

                override fun onSuccess(result: Credentials) {
                    cachedCredentials = result
                    showSnackBar("Success: ${result.accessToken}")
                    startActivity(intent)
                    showUserProfile()
                    logout()
                }
            })
    }

//    Membuat Fungsi Logout
    private fun logout() {
        WebAuthProvider.logout(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, object : Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    // The user has been logged out!
                    cachedCredentials = null
                    cachedUserProfile = null
//                    updateUI()
                }

                override fun onFailure(exception: AuthenticationException) {
//                    updateUI()
                    showSnackBar("Failure: ${exception.getCode()}")
                }
            })
    }

    private fun showUserProfile() {
        val client = AuthenticationAPIClient(account)

        // Menggunakan akses token untuk memanggil userInfo
        client.userInfo(cachedCredentials!!.accessToken!!)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    showSnackBar("Failure: ${exception.getCode()}")
                }

                override fun onSuccess(profile: UserProfile) {
                    cachedUserProfile = profile;
                }
            })
    }

//    Membuat sebuah Snackbar
    private fun showSnackBar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
