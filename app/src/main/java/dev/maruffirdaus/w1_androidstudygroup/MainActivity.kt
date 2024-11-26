package dev.maruffirdaus.w1_androidstudygroup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dev.maruffirdaus.w1_androidstudygroup.databinding.ActivityMainBinding
import dev.maruffirdaus.w1_androidstudygroup.databinding.DialogRegisterBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var usernamePassword: Map<String, String> = mapOf(Pair("maruffirdaus", "admin"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            if (0 < ime.bottom) {
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, ime.bottom)
            } else {
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            }
            insets
        }
        showMessageFromHomeActivity()
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun showMessageFromHomeActivity() {
        intent.getStringExtra(EXTRA_MESSAGE)?.let { message ->
            Snackbar.make(
                binding.root,
                message,
                Snackbar.LENGTH_SHORT
            ).show()
            Log.e(TAG, message)
        }
    }

    private fun login() {
        val username = binding.tfUsername.editText?.text.toString()
        val password = binding.tfPassword.editText?.text.toString()
        if (usernamePassword.containsKey(username)) {
            binding.tfUsername.error = null
            if (usernamePassword[username] == password) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(HomeActivity.EXTRA_USERNAME, username)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(
                    binding.root,
                    "Password salah",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.tfPassword.error = "Password salah"
                Log.e(TAG, "Login gagal: password salah")
            }
        } else {
            Snackbar.make(
                binding.root,
                "Username belum terdaftar",
                Snackbar.LENGTH_SHORT
            ).show()
            binding.tfUsername.error = "Username belum terdaftar"
            Log.e(TAG, "Login gagal: username belum terdaftar")
        }
    }

    private fun register() {
        val registerDialogBinding = DialogRegisterBinding.inflate(layoutInflater)
        MaterialAlertDialogBuilder(this)
            .setTitle("Daftar")
            .setView(registerDialogBinding.root)
            .setPositiveButton("Konfirmasi") { dialog, _ ->
                val username = registerDialogBinding.tfUsername.editText?.text.toString()
                val password = registerDialogBinding.tfPassword.editText?.text.toString()
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    if (usernamePassword.containsKey(username)) {
                        dialog.dismiss()
                        Snackbar.make(
                            binding.root,
                            "Pendaftaran gagal, username telah terdaftar",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.e(TAG, "Pendaftaran gagal: username telah terdaftar")
                    } else {
                        usernamePassword += Pair(username, password)
                        dialog.dismiss()
                        Snackbar.make(
                            binding.root,
                            "Pendaftaran berhasil",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        Log.d(TAG, "Pendaftaran berhasil")
                    }
                } else {
                    dialog.dismiss()
                    Snackbar.make(
                        binding.root,
                        "Pendaftaran gagal, username atau password kosong",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, "Pendaftaran gagal: username atau password kosong")
                }
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    companion object {
        const val TAG = "MainActivity"
        const val EXTRA_MESSAGE = "extra_message"
    }
}