package hr.ferit.stefanbelic.mushroom.presentation.commonComposables


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import hr.ferit.stefanbelic.mushroom.R
import hr.ferit.stefanbelic.mushroom.models.MashroomInfo

@Composable
fun MushroomListItem(
    mashroomInfo: MashroomInfo,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Box(
            modifier = Modifier.size(75.dp), contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator(modifier = Modifier.size(10.dp))

            if (mashroomInfo.image != "") {
                SubcomposeAsyncImage(
                    model = mashroomInfo.image,
                    contentDescription = "Image Loading",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),
                    onError = {

                    }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.mashroom),
                    contentDescription = "",
                    modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }

        }

        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
            Text(
                text = mashroomInfo.title,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMushroomListItem() {
    MushroomListItem(MashroomInfo("", "", "Mushroom Title"))
}