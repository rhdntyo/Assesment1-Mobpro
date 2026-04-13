package com.jek.kalkulatornilaiproduk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jek.kalkulatornilaiproduk.ui.screen.MainScreen
import com.jek.kalkulatornilaiproduk.ui.theme.KalkulatorNilaiProdukTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorNilaiProdukTheme {
                MainScreen()
            }
        }
    }
}

