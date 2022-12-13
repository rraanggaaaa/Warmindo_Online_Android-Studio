package com.example.warmindoonline
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.warmindoonline.Dashboard


class MainActivity : AppCompatActivity() {
    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("Authentication error: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    notifyUser("Authentication Success!")
                    startActivity(Intent(this@MainActivity, Dashboard::class.java))
                }
            }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_login: Button = findViewById(R.id.btn_login)
        val username: EditText = findViewById(R.id.etUsername)
        val password: EditText = findViewById(R.id.etPassword)
        val btn_signup: Button = findViewById(R.id.btn_signup)

        btn_login.setOnClickListener{
            if (username.text.isBlank() == true && password.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Username dan Password!", Toast.LENGTH_SHORT).show()
            else if (username.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Username!", Toast.LENGTH_SHORT).show()
            else if (password.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Password!", Toast.LENGTH_SHORT).show()
            else if (username.text.toString() == "rangga" && password.text.toString() == "123"){
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)}
            else {
                Toast.makeText(this, "Username dan Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }

        btn_signup.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        checkBiometricSupport()
        val button = findViewById<ImageButton>(R.id.btn_authenticate)
        button.setOnClickListener {
            val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Fingerprint")
                .setSubtitle("Authenticaion is required")
                .setDescription("Fingerprint Authentication")
                .setNegativeButton(
                    "Cancel",
                    this.mainExecutor,
                    DialogInterface.OnClickListener { dialog, which ->
                    }).build()
            biometricPrompt.authenticate(
                getCancellationSignal(),
                mainExecutor,
                authenticationCallback
            )
        }
    }

    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport(): Boolean {
        val keyguardManager: KeyguardManager =
            getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isKeyguardSecure) {
            notifyUser("Fingerprint hs not been enabled in settings.")
            return false
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notifyUser("Fingerprint hs not been enabled in settings.")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }
}