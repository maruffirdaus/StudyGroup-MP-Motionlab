package dev.maruffirdaus.w1_androidstudygroup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dev.maruffirdaus.w1_androidstudygroup.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }
        Snackbar.make(
            binding.root,
            "Login berhasil",
            Snackbar.LENGTH_SHORT
        ).show()
        Log.d(TAG, "Login berhasil")
        intent.getStringExtra(EXTRA_USERNAME)?.let { username ->
            binding.tvWelcome.text = buildString {
                append("Selamat datang, ")
                append(username)
                append("!")
            }
        }
        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Logout?")
            .setMessage("Anda akan dikeluarkan dari beranda")
            .setPositiveButton("Ya") { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_MESSAGE, "Logout berhasil")
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    companion object {
        const val TAG = "HomeActivity"
        const val EXTRA_USERNAME = "extra_username"
    }
}