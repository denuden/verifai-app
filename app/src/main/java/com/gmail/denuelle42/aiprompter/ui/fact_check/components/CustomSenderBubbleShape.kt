package com.gmail.denuelle42.aiprompter.ui.fact_check.components

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CustomSenderBubbleShape(private val cornerRadius: Float) : Shape {
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

            // --- STEP 1: START AT TOP-LEFT ---
            // Move pen to the start of the top edge (skipping the left corner)
            moveTo(cornerRadius, 0f)

            // --- STEP 2: TOP EDGE & TOP-RIGHT CORNER ---
            // Draw line to the right, stopping before the corner
            lineTo(rectWidth - cornerRadius, 0f)
            // Draw the curve: Control point is (rectWidth, 0), End point is (rectWidth, cornerRadius)
            quadraticTo(rectWidth, 0f, rectWidth, cornerRadius)

            // --- STEP 3: RIGHT EDGE ---
            // Draw line down the right side.
            // STOP condition: We subtract (bodyBottomMargin + tailWidth) to leave room for the tail curve.
            lineTo(rectWidth, rectHeight - bodyBottomMargin - tailWidth)

            // --- STEP 4: THE TAIL (The Complex Part) ---
            // We want a curve that swoops OUT to the right and DOWN to the bottom.

            // Curve OUT to the tip.
            // Control Point (rectWidth, rectHeight - tailWidth): Pulls the curve down near the edge.
            // End Point (rectWidth + tailTipStickOut, rectHeight): The sharp tip.
            // Note: rectWidth + tailTipStickOut means we actually draw OUTSIDE the standard box width!
            quadraticTo(
                rectWidth,
                rectHeight - tailWidth, // Control point
                rectWidth + tailTipStickOut, // End X (Tip)
                rectHeight // End Y (Bottom of component)
            )

            // Line BACK IN from the tip to the bubble body.
            // We go Left (rectWidth - tailWidth) and Up slightly (rectHeight - bodyBottomMargin)
            lineTo(rectWidth - tailWidth, rectHeight - bodyBottomMargin)

            // --- STEP 5: BOTTOM EDGE ---
            // Now we are at the bottom of the "Main Body".
            // Draw line to the left corner.
            lineTo(cornerRadius, rectHeight - bodyBottomMargin)

            // --- STEP 6: BOTTOM-LEFT CORNER ---
            // Standard rounded corner, but handled at the "bodyBottomMargin" height, not full height.
            quadraticBezierTo(
                0f,
                rectHeight - bodyBottomMargin, // Control point
                0f,
                rectHeight - bodyBottomMargin - cornerRadius // End point (up the left side)
            )

            // --- STEP 7: LEFT EDGE & TOP-LEFT CORNER ---
            // Draw line up to top corner start
            lineTo(0f, cornerRadius)
            // Curve back to the very start (0,0) logic
            quadraticBezierTo(0f, 0f, cornerRadius, 0f)

            close()
        }

        return androidx.compose.ui.graphics.Outline.Generic(trianglePath)
    }
}