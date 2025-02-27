package hr.ferit.stefanbelic.mushroom.presentation.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.stefanbelic.mushroom.R
import hr.ferit.stefanbelic.mushroom.Routes

@Composable
fun HomeScreen(navController: NavController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.suma),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Gljive U Šumi",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 64.dp, bottom = 32.dp)
            )


            Button(
                onClick = {
                    navController.navigate(Routes.IZBORNIK.routePath)
                },
                modifier = Modifier.size(width = 200.dp, height = 60.dp)
            ) {
                Text(text = "ISTRAŽI GLJIVE")
            }

        }
    }
}