package com.RahadiyanAristyo0052.Assesment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.RahadiyanAristyo0052.Assesment1.ui.screen.AppNavigation
import com.RahadiyanAristyo0052.Assesment1.ui.theme.KalkulatorNilaiProdukTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorNilaiProdukTheme {
                AppNavigation()
            }
        }
    }
}
