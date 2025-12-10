package com.gmail.denuelle42.aiprompter.ui.fact_check.components

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CustomReceiverBubbleShape(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): androidx.compose.ui.graphics.Outline {

        val trianglePath = Path().apply {
            val rectWidth = size.width
            val rectHeight = size.height

            // CONFIGURATION: Tweak these to change the look
            val tailWidth = 25f  // How "fat" the tail is at the base
            val tailTipStickOut = 10f // How far the tip sticks out to the right
            val bodyBottomMargin = 10f // We lift the bubble body up slightly so the tail sits below it

            // --- STEP 1: TOP-RIGHT START ---
            // Start at the top-right corner (minus radius)
            moveTo(rectWidth - cornerRadius , 0f)

            // --- STEP 2: TOP EDGE & TOP-LEFT CORNER ---
            // Draw line to the left
            lineTo(cornerRadius, 0f)
            // Draw the curve: Control point is (rectWidth, 0), End point is (rectWidth, cornerRadius)
            quadraticTo(0f, 0f, 0f, cornerRadius)

            // --- STEP 3: LEFT EDGE (Down to the tail start) ---
            // We draw down the left side, but stop early to make room for the tail
            lineTo(0f, rectHeight - bodyBottomMargin - tailWidth)

            // --- STEP 4: THE TAIL (Left Side) ---
            // This is the tricky part. We are currently on the left edge (X=0).
            // A. Curve OUT to the left (negative X)
            // Control point: (0, rectHeight - tailWidth) -> Anchors the curve
            // End point: (-tailTipStickOut, rectHeight) -> The sharp tip at the bottom
            quadraticTo(
                0f,
                rectHeight - tailWidth, // Control point
                0f - tailTipStickOut, // End X (Tip)
                rectHeight   // End Y (Bottom of component)
            )

            // B. Line BACK IN to the body
            // We draw a straight line from the tip back to where the bottom edge starts
            lineTo(tailWidth, rectHeight - bodyBottomMargin)

            // --- STEP 5: BOTTOM EDGE ---
            // Draw line from the tail end to the right corner
            lineTo(rectWidth - cornerRadius, rectHeight - bodyBottomMargin)

            // --- STEP 6: BOTTOM-RIGHT CORNER ---
            // Curve around the bottom-right corner
            quadraticBezierTo(
                rectWidth,
                rectHeight - bodyBottomMargin,
                rectWidth,
                rectHeight - bodyBottomMargin - cornerRadius
            )
            // --- STEP 7: RIGHT EDGE ---
            // Draw line up to the start
            lineTo(rectWidth, cornerRadius)

            // Close the loop back to the very start (Top-Right)
            quadraticTo(rectWidth, 0f, rectWidth - cornerRadius, 0f)

            close()
        }

        return androidx.compose.ui.graphics.Outline.Generic(trianglePath)
    }
}