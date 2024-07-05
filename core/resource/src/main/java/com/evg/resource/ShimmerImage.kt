package com.evg.resource

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.Pink80
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerImage(
    url: String,
    size: Int,
) {
    SubcomposeAsyncImage(
        model = url,
        modifier = Modifier.size(size.dp),
        contentDescription = url,
        alignment = Alignment.CenterStart,
        contentScale = ContentScale.FillBounds,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(BorderRadius))
                    .shimmer(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                )
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Image(
                    modifier = Modifier
                        .padding(5.dp),
                    painter = painterResource(id = R.drawable.error),
                    contentDescription = "Error",
                    colorFilter = ColorFilter.tint(Pink80),
                )
            }
        },
    )

}