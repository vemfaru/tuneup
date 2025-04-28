package com.vemfaru.tuneup.ios.interop

import androidx.compose.ui.window.ComposeUIViewController
import com.kmptuner.shared.HelloWorld
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { HelloWorld() }
