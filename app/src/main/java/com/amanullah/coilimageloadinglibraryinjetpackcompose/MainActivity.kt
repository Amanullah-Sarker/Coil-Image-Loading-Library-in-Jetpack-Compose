package com.amanullah.coilimageloadinglibraryinjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.amanullah.coilimageloadinglibraryinjetpackcompose.ui.theme.CoilImageLoadingLibraryInJetpackComposeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoilImageLoadingLibraryInJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CoilImageLoader()
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CoilImageLoader()
{
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(250.dp),
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberImagePainter(
            // Data can be From Server of From Drawable
            //data = "https://en.wikipedia.org/wiki/Image#/media/File:Image_created_with_a_mobile_phone.png",
            data = R.drawable.aman,
            builder = {
                placeholder(R.drawable.image)
                error(R.drawable.error)
                crossfade(1000)
                transformations(
                    //GrayscaleTransformation(),
                    //CircleCropTransformation(),
                    //BlurTransformation(LocalContext.current),
                    RoundedCornersTransformation(50f)
                )
            }
        )
        val painterState = painter.state
        Image(painter = painter, contentDescription = "Aman")
        if (painterState is ImagePainter.State.Loading)
        {
            CircularProgressIndicator()
        }else if (painterState is ImagePainter.State.Empty)
        {
            Text(
                text = "No File",
                color = Color.Black
            )
        }else if (painterState is ImagePainter.State.Error)
        {
            Text(
                text = "Error",
                color = Color.Red
            )
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoilImageLoadingLibraryInJetpackComposeTheme {
        CoilImageLoader()
    }
}