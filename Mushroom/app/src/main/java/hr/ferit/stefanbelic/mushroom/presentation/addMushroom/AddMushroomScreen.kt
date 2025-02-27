package hr.ferit.stefanbelic.mushroom.presentation.addMushroom

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.stefanbelic.mushroom.presentation.commonComposables.CircularDialog
import hr.ferit.stefanbelic.mushroom.ui.theme.BackgroundColor
import hr.ferit.stefanbelic.mushroom.ui.theme.Purple40
import hr.ferit.stefanbelic.mushroom.ui.theme.PurpleGrey80

@Composable
fun AddMushroomScreen(
    addMushroomState: State<AddMushroomScreenState>,
    mushroomTitle: (String) -> Unit = {},
    navBack: () -> Unit = {}
) {

    val context = LocalContext.current
    var text by remember { mutableStateOf("") }

    if (addMushroomState.value.isLoading) {
        CircularDialog(onDismiss = {

        })
    }

    if (addMushroomState.value.data) {
        navBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(10.dp)
    ) {

        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = "Dodaj Gljivu",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                style = TextStyle(textAlign = TextAlign.Center),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = text,
                placeholder = {
                    Text(text = "Unesite naziv gljive", color = Black.copy(alpha = 0.8F))
                },
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    backgroundColor = PurpleGrey80,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
        }

        Button(
            onClick = {

                if (text.isEmpty()) {
                    Toast.makeText(context, "Unesite gljivu", Toast.LENGTH_SHORT).show()

                    return@Button
                }

                mushroomTitle(text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(45.dp)
                .align(Alignment.BottomEnd),
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple40)
        ) {
            Text(text = "Unesi gljivu", color = Color.White)
        }

    }

}

@Preview
@Composable
fun PreviewAddMushroomScreen() {
    val addMushroomScreenState = remember {
        mutableStateOf(
            AddMushroomScreenState(
                isLoading = true
            )
        )
    }
    AddMushroomScreen(addMushroomScreenState)
}