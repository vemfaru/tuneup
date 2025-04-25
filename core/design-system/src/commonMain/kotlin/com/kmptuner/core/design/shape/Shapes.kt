package com.kmptuner.core.design.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * App shapes
 */
val AppShapes = Shapes(
    // Small components like buttons, chips, etc.
    small = RoundedCornerShape(4.dp),
    
    // Medium components like cards, dialogs, etc.
    medium = RoundedCornerShape(8.dp),
    
    // Large components like sheets, etc.
    large = RoundedCornerShape(12.dp),
    
    // Extra large components
    extraLarge = RoundedCornerShape(16.dp)
)