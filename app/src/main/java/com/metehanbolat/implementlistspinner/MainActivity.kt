package com.metehanbolat.implementlistspinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.metehanbolat.implementlistspinner.ui.theme.ImplementListSpinnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImplementListSpinnerTheme {
                
            }
        }
    }
}
