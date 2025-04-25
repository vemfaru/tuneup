package com.kmptuner.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmptuner.core.design.KmpTunerButton

/**
 * A simple Hello World composable that uses the design system
 */
@Composable
fun HelloWorld() {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        KmpTunerButton(
            text = "Hello, KMP Tuner!",
            onClick = { println("Button clicked!") }
        )
    }
}
