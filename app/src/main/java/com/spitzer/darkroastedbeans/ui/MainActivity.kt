package com.spitzer.darkroastedbeans.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.databinding.MainActivityBinding
import com.spitzer.darkroastedbeans.ui.machinepairing.MachinePairingFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    private fun setupView() {
        binding.toolbarImage.setOnClickListener {
            Toast.makeText(applicationContext, "BACK", Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressBar() {
        binding.clProgressBar.visibility = View.VISIBLE
        binding.coordinator.alpha = DIM_BACKGROUND_ALPHA
    }

    fun hideProgressBar() {
        binding.clProgressBar.visibility = View.GONE
        binding.coordinator.alpha = VISIBLE_BACKGROUND_ALPHA
    }

    fun setupCustomToolbar(title: String, mainText: String, shouldShowBackArrow: Boolean) {
        binding.toolbarTitle.text = title
        binding.toolbarMainText.text = mainText
        binding.toolbarImage.visibility =
            if (shouldShowBackArrow) View.VISIBLE else View.GONE
    }

    fun showSnackbar(message: String) {
        Snackbar.make(binding.coordinator, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val DIM_BACKGROUND_ALPHA = 0.3f
        const val VISIBLE_BACKGROUND_ALPHA = 1f
    }
}
