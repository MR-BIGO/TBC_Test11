package com.example.tbc_test11.presentation.screen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tbc_test11.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}