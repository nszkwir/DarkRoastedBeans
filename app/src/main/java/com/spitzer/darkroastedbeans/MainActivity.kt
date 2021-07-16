package com.spitzer.darkroastedbeans

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.spitzer.darkroastedbeans.databinding.MainActivityBinding
import com.spitzer.darkroastedbeans.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        setupView()
    }

    fun setupView() {
        binding.toolbarImage.setOnClickListener {
            Toast.makeText(applicationContext, "BACK", Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressBar() {
        binding.clProgressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.clProgressBar.visibility = View.GONE
    }

    fun setupCustomToolbar(title: String, mainText: String, shouldShowBackArrow: Boolean) {
        binding.toolbarTitle.text = title
        binding.toolbarMainText.text = mainText
        binding.toolbarImage.visibility = if (shouldShowBackArrow) View.VISIBLE else View.GONE
    }

}