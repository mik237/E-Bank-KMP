package me.ibrahim.ebank.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import me.ibrahim.ebank.kmp.presentation.EBankApp
import me.ibrahim.ebank.kmp.presentation.decompose.bottom_navigation.BottomNavComponentImpl
import me.ibrahim.ebank.kmp.presentation.decompose.root.EBankRootImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = BottomNavComponentImpl(defaultComponentContext())
        setContent {
            EBankApp(root = root)
        }
    }
}